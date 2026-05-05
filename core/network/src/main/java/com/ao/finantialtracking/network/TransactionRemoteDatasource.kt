package com.ao.finantialtracking.network

import com.ao.financialtracking.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRemoteDataSource {
    suspend fun addTransaction(transaction: Transaction)
    fun getTransactions(userId: String = "debug_id"): Flow<List<Transaction>>
}