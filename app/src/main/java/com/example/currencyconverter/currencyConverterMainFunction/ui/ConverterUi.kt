package com.example.currencyconverter.currencyConverterMainFunction.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ConverterUi(viewModel: CurrencyViewModel) {
    val krw by viewModel.krwInput.collectAsState()
    val usd by viewModel.usdResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Currency Converter (KRW → USD)")

        OutlinedTextField(
            value = krw,
            onValueChange = viewModel::onKrwChange,
            label = { Text("Amount (₩ KRW)") },
            placeholder = { Text("e.g. 10000") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Exchange rate: 1 KRW ≈ 0.00075 USD")

        Button(
            onClick = viewModel::convertCurrency,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Convert")
        }

        if (usd.isNotEmpty()) {
            Text(
                text = "💵 Result: $usd USD",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}