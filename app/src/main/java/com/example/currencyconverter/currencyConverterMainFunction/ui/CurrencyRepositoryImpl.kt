package com.example.currencyconverter.currencyConverterMainFunction.ui

import com.example.currencyconverter.currencyConverterMainFunction.api.CurrencyApiService
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService
) : CurrencyRepository {
    override suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {

        return apiService.getExchangeRate(from, to, amount)
    }
}