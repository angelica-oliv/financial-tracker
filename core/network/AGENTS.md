# Module Agent Instructions: :core:network

## 🎯 Purpose
Handles infrastructure, networking, and data persistence logic. It manages the connection to Firebase and provides the global Moshi instance.

## 🛠 Tech Stack
- **Firebase**: Firestore for database, Auth for identity.
- **Moshi**: KSP-based serialization (no reflection).
- **Hilt**: Provides dependencies to the rest of the app.

## 🛡 Guidelines
- **Pragmatic Persistence**: Map domain models directly to Firestore collections.
- **Coroutines**: Use `.await()` (from `kotlinx-coroutines-play-services`) for one-shot Firebase operations.
- **Reactive Data**: Return `Flow` for real-time Firestore updates using `.snapshots()`.
- **Visibility**: Keep implementation details `internal` where possible, exposing only the public API (e.g., DataSources, Repositories).

## 📦 Key Components
- `NetworkModule`: Hilt module providing `Moshi` and `FirebaseFirestore`.
- `TransactionRemoteDataSource`: (Planned/In-progress) Manages `Transaction` persistence in Firestore.
