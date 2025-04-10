package com.example.currencyconverter.currencyConverterMainFunction.ui

import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {
    override suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {
        // Placeholder: 실제로는 Ktor API 연동 예정
        return amount * 0.00075
    }
}