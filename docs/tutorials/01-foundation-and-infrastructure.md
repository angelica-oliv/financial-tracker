# Tutorial 01: Project Foundation & Infrastructure

This tutorial covers the initial setup of the project, including modularization strategy, financial domain modeling, and core infrastructure like DI and Serialization.

## 1. Modularization & Parallel Compilation

As projects grow, build times increase and code becomes tangled. Modularization solves this by breaking the project into smaller, independent units.

### How Parallel Compilation Works
In a monolithic project, Gradle treats the entire codebase as one large unit. This means the compilation process is largely sequential; even if you have a 16-core processor, much of that power sits idle because the tasks must be done one after another.

In a modular project, Gradle analyzes the **Dependency Graph**. This graph identifies which modules are independent of each other. 

**The Multi-Core Advantage:**
- If `:feature:dashboard` and `:feature:settings` both depend on `:core:ui`, but do **not** depend on each other, Gradle will spin up separate "workers" to compile them **simultaneously** on different CPU cores.
- This transforms your build process from a single-file line into a multi-lane highway.

**Benefits:**
- **Reduced "Wall Clock" Time**: Total build time is determined by the longest path in your dependency graph (the "critical path"), rather than the sum of every single module's build time.
- **Incremental Builds**: Gradle's build cache works at the module level. If you modify a feature module, only that module and its consumers (like `:app`) are recompiled. Independent modules like `:core:network` are pulled directly from the cache, saving minutes of developer time every day.

### Our Strategy
- `:app`: The entry point and glue for all modules.
- `:core:model`: Pure Kotlin module for domain models (no Android dependencies).
- `:core:network`: Infrastructure for data fetching (Firebase/Moshi).
- `:core:ui`: Common design system components.
- `:feature:*`: Independent modules for specific app features.

---

## 2. Domain Modeling & Financial Precision

We represent money using `Long` to avoid rounding errors common with `Double` or `Float`.
- `$10.50` is stored as `1050` (cents).
- **KDoc**: Always document the units (e.g., "value in cents") to ensure clarity across the team.

```kotlin
@JsonClass(generateAdapter = true)
data class Transaction(
    val id: String = "",
    val value: Long = 0L,
    val currency: String = "USD",
    val type: TransactionType = TransactionType.EXPENSE,
    val timestamp: Long = 0L,
    val category: String = ""
)
```

---

## 3. Infrastructure: Moshi & Hilt

### Moshi with KSP (No Reflection)
We use **KSP (Kotlin Symbol Processing)** to generate JSON adapters at compile time. This is faster than reflection (`moshi-kotlin`) and works better with R8/ProGuard minification.

### Hilt for Dependency Injection
Hilt provides a standard way to incorporate Dagger DI into an Android app.
- **`@HiltAndroidApp`**: Required on your `Application` class.
- **Centralized Modules**: We use `:core:network` to provide singletons like `Moshi` and `FirebaseFirestore` so they are consistent throughout the app.
- **Plugin Management**: Plugins are defined in `libs.versions.toml` and the root `build.gradle.kts` with `apply false` to centralize versions without applying them to the root project itself.
