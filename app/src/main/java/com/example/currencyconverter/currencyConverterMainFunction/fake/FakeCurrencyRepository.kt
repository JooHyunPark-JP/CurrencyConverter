package com.example.currencyconverter.currencyConverterMainFunction.fake

import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyInfo
import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyRepository

//it's for unit testing
class FakeCurrencyRepository : CurrencyRepository {

    override suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {
        val validCurrencies = listOf("USD", "KRW", "JPY", "EUR", "GBP", "AUD", "CAD")
        if (!validCurrencies.contains(to)) {
            throw Exception("Invalid target currency")
        }
        val fakeRate = 0.00123
        return amount * fakeRate
    }

    override suspend fun getSupportedCurrencies(): List<CurrencyInfo> {
        return listOf(
            CurrencyInfo("USD", "United States Dollar"),
            CurrencyInfo("KRW", "South Korean Won"),
            CurrencyInfo("JPY", "Japanese Yen"),
            CurrencyInfo("EUR", "Euro"),
            CurrencyInfo("GBP", "British Pound Sterling"),
            CurrencyInfo("AUD", "Australian Dollar"),
            CurrencyInfo("CAD", "Canadian Dollar")
        )
    }
}