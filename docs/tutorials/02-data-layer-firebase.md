# Tutorial 02: Data Layer - Firebase Integration

Integrating cloud storage with Firestore while maintaining a clean architecture.

## Direct Domain Persistence
While DTOs (Data Transfer Objects) are often used to decouple the database from the domain, we chose a **Pragmatic Approach**:
1. Provide **default values** in the `Transaction` domain model.
2. Firestore can then use the domain model directly via `toObject()` and `set()`.
3. This reduces boilerplate in the early stages of development.

## The Remote Data Source
The `TransactionRemoteDataSource` handles the "how" of data fetching:
- Use `kotlinx-coroutines-play-services` to turn Firebase Task callbacks into `suspend` functions using `.await()`.
- Use `.snapshots()` to return a `Flow` for real-time updates.

## UI Separation: The Content Class
To prevent the UI from being tightly coupled to the raw data (like `Long` cents), we map the `Transaction` to a `TransactionContent` class in the UI layer. This handles formatting (e.g., "$10.50") and UI logic (e.g., colors and icons).
