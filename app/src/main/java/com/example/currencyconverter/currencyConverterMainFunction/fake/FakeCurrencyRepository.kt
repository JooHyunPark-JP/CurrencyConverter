package com.example.currencyconverter.currencyConverterMainFunction.fake

import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyRepository

//it's for unit testing
class FakeCurrencyRepository : CurrencyRepository {

    override suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {
        val fakeRate = 0.00123
        return amount * fakeRate
    }

    override suspend fun getSupportedCurrencies(): List<String> {
        TODO("Not yet implemented")
    }
}