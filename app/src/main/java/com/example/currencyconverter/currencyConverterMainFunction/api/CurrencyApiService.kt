package com.example.currencyconverter.currencyConverterMainFunction.api

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyInfo
import com.example.currencyconverter.currencyConverterMainFunction.data.SupportedCodesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
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
        val url =
            "https://v6.exchangerate-api.com/v6/${BuildConfig.CURRENCY_API_KEY}/pair/$from/$to/$amount"
        val response: HttpResponse = client.get(url)
        val json = response.body<ExchangeRateResponse>()
        return json.conversionResult
    }

    suspend fun getSupportedCurrencies(): List<CurrencyInfo> {
        val url = "https://v6.exchangerate-api.com/v6/${BuildConfig.CURRENCY_API_KEY}/codes"
        val response: HttpResponse = client.get(url)
        val result = response.body<SupportedCodesResponse>()
        return result.supportedCodes.map { CurrencyInfo(it[0], it[1]) }
    }

    /*    @Serializable
        data class ExchangeRateResponse(
            val result: Double
        )*/

    @Serializable
    data class ExchangeRateResponse(
        val result: String,
        @SerialName("conversion_result")
        val conversionResult: Double
    )
}
