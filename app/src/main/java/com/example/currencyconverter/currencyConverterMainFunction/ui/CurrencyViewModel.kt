package com.example.currencyconverter.currencyConverterMainFunction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyUiState())
    val uiState: StateFlow<CurrencyUiState> = _uiState

    fun onInputChange(value: String) {
        _uiState.update { it.copy(input = value) }
    }

    fun onFromCurrencyChange(value: String) {
        _uiState.update { it.copy(from = value) }
    }

    fun onToCurrencyChange(value: String) {
        _uiState.update { it.copy(to = value) }
    }

    fun convertCurrency() {
        viewModelScope.launch {
            val amount = _uiState.value.input.toDoubleOrNull() ?: 0.0
            try {
                val rate = repository.getExchangeRate(
                    from = _uiState.value.from,
                    to = _uiState.value.to,
                    amount = amount
                )
                _uiState.update { it.copy(result = "%.2f".format(rate), error = null) }
            } catch (e: Exception) {
                _uiState.update { it.copy(result = "", error = "Failed: ${e.message}") }
            }
        }
    }
}