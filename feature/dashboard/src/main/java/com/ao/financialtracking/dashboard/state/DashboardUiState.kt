package com.ao.financialtracking.dashboard.state

import android.content.Context
import com.ao.financialtracking.ui.R
import com.ao.financialtracking.model.Transaction
import com.ao.financialtracking.model.TransactionType
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale
import java.text.NumberFormat

data class TransactionContent(
    val id: String,
    val amount: String,
    val category: String,
    val type: TransactionType,
)

data class TransactionGroupContent(
    val date: String,
    val transactions: List<TransactionContent>
)

data class DashboardUiState(
    val transactions: List<TransactionGroupContent> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

fun List<Transaction>.toDashboardUiState(context: Context): DashboardUiState {
    val groups = this
        .sortedByDescending { it.timestamp }
        .groupBy { it.timestamp.toLocalDate(context) }
        .map { (date, transactions) ->
            TransactionGroupContent(
                date = date,
                transactions = transactions.map { it.toContent() }
            )
        }
    
    return DashboardUiState(
        transactions = groups,
        isLoading = false,
        errorMessage = null
    )
}

fun Transaction.toContent(): TransactionContent {
    return TransactionContent(
        id = id,
        amount = value.toCurrencyString(currency),
        category = category,
        type = type
    )
}

fun Long.toCurrencyString(currencyCode: String): String {
    val amount = this / 100.0
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    try {
        format.currency = Currency.getInstance(currencyCode)
    } catch (_: Exception) {
        // Fallback or log error
    }
    return format.format(amount)
}

fun Long.toLocalDate(context: Context): String {
    val date = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    val formatter = DateTimeFormatter.ofPattern("MMMd", Locale.getDefault())
    val dateString = date.format(formatter)

    return when (date) {
        today -> context.getString(R.string.date_with_prefix, context.getString(R.string.today), dateString)
        yesterday -> context.getString(R.string.date_with_prefix, context.getString(R.string.yesterday), dateString)
        else -> dateString
    }
}
