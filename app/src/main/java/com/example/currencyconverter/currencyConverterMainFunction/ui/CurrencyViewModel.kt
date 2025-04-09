package com.example.currencyconverter.currencyConverterMainFunction.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor() : ViewModel() {

    private val _krwInput = MutableStateFlow("")
    val krwInput: StateFlow<String> = _krwInput

    private val _usdResult = MutableStateFlow("")
    val usdResult: StateFlow<String> = _usdResult

    fun onKrwChange(newValue: String) {
        _krwInput.value = newValue
    }

    fun convertCurrency() {
        val krw = _krwInput.value.toDoubleOrNull() ?: 0.0
        val rate = 0.00075
        val usd = krw * rate
        _usdResult.value = "%.2f".format(usd)
    }
}