package com.example.currencyconverter.currencyConverterMainFunction.ui

interface CurrencyRepository {
    suspend fun getExchangeRate(from: String, to: String, amount: Double): Double
}