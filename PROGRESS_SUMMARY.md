# Financial Tracker: Project Progress & Future Goals

This document summarizes the engineering milestones achieved so far and outlines the remaining roadmap to complete the application.

---

## ✅ Completed Milestones

### 1. Foundation & Architecture
- **Multi-Module Structure**: Established a scalable architecture with clear separation of concerns:
    - `:app`: The dependency injection and navigation hub.
    - `:core:model`: Pure Kotlin domain models (POJOs).
    - `:core:network`: Infrastructure for Firebase and Serialization.
    - `:core:ui`: Centralized design system (Colors, Type, Spacing).
    - `:feature:dashboard`: The first feature module following the modern Android Architecture.
- **Dependency Management**: Centralized all versions using a **Gradle Version Catalog** (`libs.versions.toml`).
- **Build System**: Modernized the build pipeline using **KSP** (replacing KAPT) and the **Kotlin 2.0 Compose Compiler** plugin.

### 2. Core Infrastructure
- **Dependency Injection**: Full **Hilt** integration with `@HiltAndroidApp`, `@Module`, and `@Binds` for interface-to-implementation mapping.
- **Serialization**: Configured **Moshi with KSP** for compile-time JSON adapter generation (zero reflection).
- **Firebase Integration**: 
    - Successfully integrated **Firestore** and **Authentication**.
    - Applied the **Google Services plugin** for automatic initialization.
    - Implemented a **Sub-collection data pattern** (`users/{id}/transactions`) for scalable multi-user data.

### 3. Domain & Data Layer
- **Financial Precision**: Implemented the `Transaction` model using `Long` for cents and timestamps, ensuring zero rounding errors and efficient sorting.
- **Remote Data Source**: Created the `TransactionRemoteDataSource` implementation using Firestore `.snapshots()` for real-time `Flow` updates and `.set()` for one-shot writes.

### 4. UI & Design System
- **Luminous Finance Design System**: Translated Stitch design tokens into a professional Compose theme:
    - **Azure Blue & Mint Green** color palette.
    - **Inter** typography hierarchy.
    - **Custom Spacing System** (8px grid) via CompositionLocal.
- **Type-Safe Navigation**: Implemented the latest **Jetpack Navigation** pattern using `@Serializable` route objects instead of string paths.
- **Dashboard Feature**: Built a `DashboardViewModel` that transforms raw data into a grouped UI State (`Map<String, List<Transaction>>`) using `StateFlow`.

---

## 🎯 Current Goals (Short-Term)

### 1. Complete the Dashboard UI
- **Transaction Item**: Implement the custom row component with category icons and financial color logic (Green for income, Slate for expenses).
- **Search & Filter**: Add logic to filter transactions by text or category (Income/Expense/All).
- **Seed Data**: Improve the "Add Dummy" functionality to test different categories and dates.

### 2. Firebase Authentication
- **Identity Logic**: Move from the hardcoded `debug_id` to real Firebase Anonymous or Email authentication.
- **Session Management**: Ensure the data source correctly listens to the currently logged-in user.

### 3. Refine Architecture
- **Repository Layer**: Introduce a `TransactionRepository` in a new `:core:data` module to act as the single source of truth, abstracting the `RemoteDataSource`.
- **Formatting Mappers**: Move string formatting (currency/dates) into a dedicated mapper layer to keep the UI "dumb."

---

## 🚀 Future Roadmap (Long-Term)
- **Feature: Add Transaction**: A dedicated screen with form validation for creating new entries.
- **Feature: Analytics/Reports**: Visualizing spending habits using charts (mapping domain data to visual representations).
- **Local Persistence**: Integrating **Room** as a local cache for offline-first capabilities.
- **Unit & UI Testing**: Implementing 70%+ code coverage for business logic and UI components.

---

## 📚 Study Strategy
- **Tutorial Integration**: Update the `docs/tutorials` folder after each major milestone to reinforce learning.
- **Time-Boxed Sprints**: Focus on 1-2 hour sessions to practice building features from scratch in a production-ready way.
