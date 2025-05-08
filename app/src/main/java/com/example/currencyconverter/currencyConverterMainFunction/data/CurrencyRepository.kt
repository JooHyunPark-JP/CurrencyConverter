package com.example.currencyconverter.currencyConverterMainFunction.data

interface CurrencyRepository {
    suspend fun getExchangeRate(from: String, to: String, amount: Double): Double
    suspend fun getSupportedCurrencies(): List<CurrencyInfo>
}