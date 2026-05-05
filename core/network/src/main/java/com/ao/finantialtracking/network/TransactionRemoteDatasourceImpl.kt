package com.ao.finantialtracking.network

import com.ao.financialtracking.model.Transaction
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): TransactionRemoteDataSource {
    override suspend fun addTransaction(transaction: Transaction) {
        firestore.collection("users")
            .document("debug_id")
            .collection("transactions")
            .document(transaction.id)
            .set(transaction)
    }

    override fun getTransactions(userId: String): Flow<List<Transaction>> {
        return firestore.collection("users")
            .document(userId)
            .collection("transactions")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(Transaction::class.java)
            }
    }
}