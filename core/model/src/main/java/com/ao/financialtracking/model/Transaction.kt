package com.ao.financialtracking.model

import com.squareup.moshi.JsonClass

/**
 * Represents a financial transaction.
 *
 * @property id Unique identifier for the transaction (e.g., Firebase UID).
 * @property value The monetary value of the transaction in the smallest currency unit (e.g., cents).
 * @property currency The ISO 4217 currency code (e.g., "USD").
 * @property type Whether the transaction is an [TransactionType.EXPENSE] or [TransactionType.INCOME].
 * @property timestamp The time the transaction occurred, in milliseconds since epoch.
 * @property category The category associated with the transaction.
 */
@JsonClass(generateAdapter = true)
data class Transaction(
    val id: String = "",
    val value: Long = 0,
    val currency: String = "USD",
    val type: TransactionType = TransactionType.EXPENSE,
    val timestamp: Long = 0,
    val category: String = "Shopping - Clothing"
)

enum class TransactionType {
    EXPENSE,
    INCOME
}