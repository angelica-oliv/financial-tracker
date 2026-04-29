# Financial Tracker

A modern, modular Android application built to demonstrate professional engineering practices and clean architecture. This project serves as a study guide for building high-quality Android apps from scratch.

## 📱 Features (In Progress)
- **Financial Precision**: Accurate money management using the smallest currency units.
- **Real-time Sync**: Firebase Firestore integration for seamless data persistence.
- **Modular Architecture**: Scalable project structure designed for fast builds and testability.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3 Expressive.

## 🏗 Architecture & Tech Stack
The app follows the **Official Guide to App Architecture** with a focus on Unidirectional Data Flow (UDF).

- **UI Layer**: Jetpack Compose, Navigation Component.
- **Domain Layer**: Pure Kotlin models and Business Logic.
- **Data Layer**: Repository pattern, Firebase Firestore, Firebase Auth.
- **Modularization Strategy**:
    - `:app`: Entry point & Dependency Injection glue.
    - `:core:model`: Shared domain entities.
    - `:core:network`: Remote data sources and infrastructure (Moshi/Hilt).
    - `:core:ui`: Shared design system and common components.
    - `:feature:*`: Independent modules for feature-specific logic.

### Technology Highlights:
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) with KSP for compile-time safety.
- **Serialization**: [Moshi](https://github.com/square/moshi) with KSP code generation (No reflection).
- **Concurrency**: Kotlin Coroutines & Flow for reactive programming.
- **Build System**: Kotlin DSL, Version Catalogs (`libs.versions.toml`).

## 🎓 Study Guide & Tutorials
This project is documented in a tutorial format to help developers understand the reasoning behind each architectural decision.

You can find the step-by-step guides in the [docs/](docs/) folder:
1. [Project Foundation & Infrastructure](docs/tutorials/01-foundation-and-infrastructure.md) - Modularization, Parallel Compilation, and DI.
2. [Data Layer: Firebase Integration](docs/tutorials/02-data-layer-firebase.md) - Real-time persistence and pragmatic domain mapping.

## 🚀 Getting Started
1. Clone the repository.
2. Open the project in the latest version of **Android Studio**.
3. Create a project on the [Firebase Console](https://console.firebase.google.com/).
4. Add your `google-services.json` to the `app/` directory.
5. Sync Gradle and run the `:app` module.

## 📄 License
This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
