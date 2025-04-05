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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ConverterUi(
    krw: String = "",
    onKrwChange: (String) -> Unit = {},
    usdResult: String = "",
    onConvertClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "KRW → USD 환율 변환기",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = krw,
            onValueChange = onKrwChange,
            label = { Text("금액 (₩ KRW)") },
            placeholder = { Text("예: 10000") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "현재 환율: 1 KRW ≈ 0.00075 USD",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = onConvertClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("환전하기")
        }

        if (usdResult.isNotEmpty()) {
            Text(
                text = "💵 결과: $usdResult USD",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}