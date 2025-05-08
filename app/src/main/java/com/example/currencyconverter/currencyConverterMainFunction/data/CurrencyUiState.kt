package com.example.currencyconverter.currencyConverterMainFunction.data

data class CurrencyUiState(
    val from: String = "KRW",
    val to: String = "USD",
    val input: String = "",
    val result: String = "",
    val error: String? = null,
    val currencyList: List<CurrencyInfo> = emptyList()
)