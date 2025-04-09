package com.example.currencyconverter

import com.example.currencyconverter.currencyConverterMainFunction.ui.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain


import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: CurrencyViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CurrencyViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `convertCurrency should calculate correct USD value`() = runTest(testDispatcher) {
        viewModel.onKrwChange("10000")
        viewModel.convertCurrency()
        assertEquals("7.50", viewModel.usdResult.value)
    }

    @Test
    fun `convertCurrency with invalid input should return 0_00`() = runTest(testDispatcher) {
        viewModel.onKrwChange("abc") // ← invalid input
        viewModel.convertCurrency()
        assertEquals("0.00", viewModel.usdResult.value)
    }
}