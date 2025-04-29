package com.example.currencyconverter.currencyConverterMainFunction.data

import kotlinx.serialization.Serializable

@Serializable
data class SupportedCodesResponse(
    val supported_codes: List<List<String>>
)