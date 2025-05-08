package com.example.currencyconverter.currencyConverterMainFunction.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.currencyConverterMainFunction.data.CurrencyUiState

@Composable
fun ConverterUi(
    state: CurrencyUiState,
    onInputChange: (String) -> Unit,
    onFromCurrencyChange: (String) -> Unit,
    onToCurrencyChange: (String) -> Unit,
    onConvertClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Currency Converter",
            style = MaterialTheme.typography.headlineSmall
        )

        // From Currency Dropdown
        CurrencyDropdown(
            label = "From",
            selected = state.from,
            options = state.currencyList,
            onSelect = { onFromCurrencyChange(it.code) }
        )

        // To Currency Dropdown
        CurrencyDropdown(
            label = "To",
            selected = state.to,
            options = state.currencyList,
            onSelect = { onToCurrencyChange(it.code) }
        )

        OutlinedTextField(
            value = state.input,
            onValueChange = onInputChange,
            label = { Text("Amount (${state.from})") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onConvertClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Convert")
        }

        if (state.result.isNotEmpty()) {
            Text(
                text = "💱 ${state.input} ${state.from} = ${state.result} ${state.to}",
                style = MaterialTheme.typography.titleMedium
            )
        }

        state.error?.let {
            Text(text = it, color = Color.Red)
        }
    }
}