package com.example.currencyconverter.currencyConverterMainFunction.api

import com.example.currencyconverter.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject


class CurrencyApiService @Inject constructor() {

    val key = BuildConfig.CURRENCY_API_KEY

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })

        }
        expectSuccess = false
    }


    suspend fun getExchangeRate(from: String, to: String, amount: Double): Double {
        val response: HttpResponse =
            client.get("https://v6.exchangerate-api.com/v6/${BuildConfig.CURRENCY_API_KEY}/latest/$from")

        val result: ExchangeRateResponse = response.body()

        val rate = result.conversion_rates[to] ?: throw Exception("Invalid target currency")

        return amount * rate
    }

    @Serializable
    data class ExchangeRateResponse(
        val result: String,
        val conversion_rates: Map<String, Double>
    )
}
