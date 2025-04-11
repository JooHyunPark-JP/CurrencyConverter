package com.example.currencyconverter.currencyConverterMainFunction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _krwInput = MutableStateFlow("")
    val krwInput: StateFlow<String> = _krwInput

    private val _usdResult = MutableStateFlow("")
    val usdResult: StateFlow<String> = _usdResult

    fun onKrwChange(newValue: String) {
        _krwInput.value = newValue
    }

    fun convertCurrency() {
        viewModelScope.launch {
            val amount = _krwInput.value.toDoubleOrNull() ?: 0.0
            val rate = repository.getExchangeRate("KRW", "USD", amount)
            _usdResult.value = "%.2f".format(rate)
        }
    }
}