package com.example.currencyconverter.currencyConverterMainFunction.fake

import com.example.currencyconverter.currencyConverterMainFunction.ui.CurrencyRepository

class FakeCurrencyRepository : CurrencyRepository {

    override suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {
        val fakeRate = 0.00123
        return amount * fakeRate
    }
}