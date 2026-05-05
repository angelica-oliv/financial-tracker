# Tutorial 01: Project Foundation & Infrastructure

This tutorial covers the initial setup of the project, including modularization strategy, financial domain modeling, and core infrastructure like DI and Serialization.

## 1. Modularization & Parallel Compilation

Modularization breaks the project into independent units. This allows Gradle to use its **Dependency Graph** to compile modules simultaneously.

### Step 1: Version Catalog (`gradle/libs.versions.toml`)
We centralize all versions and libraries here.
```toml
[versions]
agp = "9.2.0"
kotlin = "2.3.21"
hilt = "2.59.2"
moshi = "1.15.2"

[libraries]
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
moshi = { group = "com.squareup.moshi", name = "moshi", version.ref = "moshi" }
moshi-kotlin-codegen = { group = "com.squareup.moshi", name = "moshi-kotlin-codegen", version.ref = "moshi" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```

### Step 2: Root Plugin Management (`build.gradle.kts`)
We use `apply false` to define versions at the root without applying them to the root project itself.
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
```

> **Important**: Starting in Kotlin 2.0, the `kotlin-compose` compiler plugin is required when `compose = true` is enabled in any module.

## 2. Domain Modeling & Financial Precision

### Step 3: Pure Kotlin Model (`:core:model`)
We use `Long` for cents to avoid rounding errors. Providing default values allows Firestore to instantiate the class automatically.
```kotlin
@JsonClass(generateAdapter = true)
data class Transaction(
    val id: String = "",
    val value: Long = 0L, // Store as cents
    val currency: String = "USD",
    val type: TransactionType = TransactionType.EXPENSE,
    val timestamp: Long = 0L,
    val category: String = ""
)
```

## 3. Infrastructure: Moshi & Hilt

### Step 4: Network Infrastructure (`:core:network`)
**NetworkModule.kt:**
We use a companion object within an abstract class to support both `@Binds` (for interfaces) and `@Provides` (for external dependencies).
```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindTransactionRemoteDataSource(
        impl: TransactionRemoteDataSourceImpl
    ): TransactionRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().build()
        
        @Provides
        @Singleton
        fun provideFirestore(): FirebaseFirestore = Firebase.firestore
    }
}
```
