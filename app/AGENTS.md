# Module Agent Instructions: :app

## 🎯 Purpose
The main entry point of the application. It acts as the "glue" that brings all feature and core modules together.

## 🛡 Guidelines
- **Application Class**: `FinancialTrackingApp` must be annotated with `@HiltAndroidApp`.
- **Navigation**: The main navigation graph resides here, coordinating transitions between feature modules.
- **Dependency Injection**: This module is responsible for initializing the Hilt dependency graph.
- **Manifest**: register activities, services, and the `Application` class here.
- **Minimal Logic**: Keep this module lean. Business logic belongs in `:core` or `:feature` modules.
