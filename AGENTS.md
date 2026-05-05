# Project Agent Instructions: Financial Tracker

This file provides context and instructions for AI agents working on this project.

## 📋 Project Goal
Build a modern, modular Android application from scratch following professional engineering practices.

## 🛠 Tech Stack
- **Language**: Kotlin (Coroutines, Flow)
- **UI**: Jetpack Compose (Material 3 Expressive)
- **DI**: Hilt
- **JSON**: Moshi (KSP - No reflection)
- **Backend**: Firebase (Firestore/Auth)
- **Architecture**: Guide to App Architecture (UI -> Domain -> Data) with Unidirectional Data Flow (UDF).

## 🏗 Modularization Strategy
- `:core:network`: Infrastructure for data fetching, Firebase, and Moshi setup.
- `:core:ui`: Common design system and UI components.
- `:core:model`: Pure Kotlin domain models.
- `:feature:*`: Independent feature modules (Dashboard, Expenses, etc.).

## 🛡 Key Guidelines
- **Financial Precision**: Use `Long` (cents/micros) for monetary values. Never use `Double` or `Float`.
- **KDoc Documentation**: Always document domain models, especially units of measure.
- **KSP Over KAPT**: Prefer KSP for build performance.
- **Pragmatic Persistence**: Use Domain models directly with Firestore by providing default values (no DTOs unless strictly necessary for complex mapping).
- **UI Decoupling**: Map Domain models to `Content` classes in the UI layer to keep Composables stable and formatting-free.
- **Design Alignment**: Use the `DESIGN.md` file in the root for all design tokens (colors, spacing, etc.). This project supports the **Stitch MCP server** for automated design-to-code syncing.
- **Documentation**: Keep the `docs/` folder updated with tutorial-style explanations for major architectural changes.
- **Licensing**: Apache License 2.0.
