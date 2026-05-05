package com.ao.financialtracking.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ao.financialtracking.dashboard.state.DashboardUiState
import com.ao.financialtracking.dashboard.state.toDashboardUiState
import com.ao.financialtracking.model.Transaction
import com.ao.financialtracking.model.TransactionType
import com.ao.finantialtracking.network.TransactionRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource,
    @param:ApplicationContext private val context: Context,
) : ViewModel() {
    val uiState: StateFlow<DashboardUiState> = remoteDataSource
        .getTransactions()
        .map { transactions ->
            transactions.toDashboardUiState(context)
        }
        .catch { error ->
            emit(DashboardUiState(errorMessage = error.message))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardUiState(isLoading = true)
        )

    fun addDummyTransaction() {
        viewModelScope.launch {
            remoteDataSource.addTransaction(
                Transaction(
                    id = UUID.randomUUID().toString(),
                    value = 1000L, // $10.00
                    currency = "USD",
                    type = TransactionType.EXPENSE,
                    timestamp = System.currentTimeMillis(),
                    category = "Food"
                )
            )
        }
    }
}