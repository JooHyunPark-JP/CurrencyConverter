package com.example.currencyconverter.currencyConverterMainFunction.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedCodesResponse(
    @SerialName("supported_codes")
    val supportedCodes: List<List<String>>
)