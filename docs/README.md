# Financial Tracker - Android Development Tutorial

Welcome to the documentation for the Financial Tracker project. This repository is designed as a study guide for building a modern, modular, and professional-grade Android application from scratch.

## 📋 Project Context
This project demonstrates professional engineering practices using the following tech stack:
- **Language**: Kotlin (Coroutines, Flow)
- **UI**: Jetpack Compose (Material 3 Expressive)
- **DI**: Hilt
- **JSON**: Moshi (KSP - No reflection)
- **Backend**: Firebase (Firestore/Auth)
- **Architecture**: Guide to App Architecture (UI -> Domain -> Data) with UDF

## 🎓 Tutorials
These tutorials follow the step-by-step progress of the app development.

1. [Project Foundation & Infrastructure](tutorials/01-foundation-and-infrastructure.md)
2. [Data Layer: Firebase Integration](tutorials/02-data-layer-firebase.md)

## 🚀 Key Engineering Decisions
- **Long for Currency**: Why we use cents/micros instead of Double.
- **KSP over KAPT**: Modernizing the build pipeline for speed.
- **Modularization**: Decoupling features to improve build times and maintainability.
