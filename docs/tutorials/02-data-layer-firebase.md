# Tutorial 02: Data Layer - Firebase Integration

This tutorial explains how we integrate Firebase Firestore while maintaining a clean, pragmatic architecture.

## 1. Firebase Configuration

### Step 1: Module Dependencies
In `:core:network/build.gradle.kts`, we use the Firebase BoM to manage versions and apply the `google-services` plugin in the `:app` module.
```kotlin
dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
}
```

## 2. Remote Data Source

### Step 2: Implementation with Flow
The `TransactionRemoteDataSourceImpl` uses `snapshots()` to return a real-time `Flow` of transaction lists.
```kotlin
class TransactionRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : TransactionRemoteDataSource {
    override fun getTransactions(userId: String): Flow<List<Transaction>> {
        return firestore.collection("users")
            .document(userId)
            .collection("transactions")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(Transaction::class.java)
            }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        firestore.collection("users")
            .document("debug_id")
            .collection("transactions")
            .document(transaction.id)
            .set(transaction)
    }
}
```

## 3. Pragmatic Persistence

### Step 3: Direct Domain Mapping
By providing default values in the `Transaction` domain model, Firestore can instantiate the class directly using `toObjects(Transaction::class.java)`. This avoids the boilerplate of creating separate DTO (Data Transfer Object) classes during early development.

---
Next: [UI & Design System Integration](03-ui-and-design-system.md)
