import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

val googleApiKey = localProperties.getProperty("GOOGLE_API_KEY") ?: ""

tasks.register("generateGoogleServicesJson") {
    val templateFile = file("google-services.json.template")
    val jsonFile = file("google-services.json")
    
    inputs.file(templateFile)
    inputs.property("googleApiKey", googleApiKey)
    outputs.file(jsonFile)
    
    doLast {
        if (templateFile.exists()) {
            val content = templateFile.readText()
            val updatedContent = content.replace("GOOGLE_API_KEY_PLACEHOLDER", googleApiKey)
            jsonFile.writeText(updatedContent)
        }
    }
}

tasks.named("preBuild") {
    dependsOn("generateGoogleServicesJson")
}

tasks.matching { it.name.contains("GoogleServices") && it.name != "generateGoogleServicesJson" }.configureEach {
    dependsOn("generateGoogleServicesJson")
}

android {
    namespace = "com.ao.financialtracking"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ao.financialtracking"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(project(":core:ui"))
    implementation(project(":feature:dashboard"))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
