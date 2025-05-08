package com.example.currencyconverter


import com.example.currencyconverter.currencyConverterMainFunction.fake.FakeCurrencyRepository
import com.example.currencyconverter.currencyConverterMainFunction.ui.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        val fakeRepo = FakeCurrencyRepository()
        viewModel = CurrencyViewModel(fakeRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `convertCurrency should calculate using fake exchange rate`() = runTest(testDispatcher) {
        viewModel.onInputChange("10000")
        viewModel.convertCurrency()
        val result = viewModel.uiState.value.result
        assertEquals("12.30", result) // 10000 * 0.00123
    }

    @Test
    fun `convertCurrency with invalid input should result in 0_00`() = runTest(testDispatcher) {
        viewModel.onInputChange("abc")
        viewModel.convertCurrency()
        val result = viewModel.uiState.value.result
        assertEquals("0.00", result)
    }

    @Test
    fun `convertCurrency should update error when exception thrown`() = runTest(testDispatcher) {
        viewModel.onToCurrencyChange("INVALID")
        viewModel.onInputChange("1000")
        viewModel.convertCurrency()
        val error = viewModel.uiState.value.error
        assertTrue(error?.contains("Invalid target currency") == true)
    }

    @Test
    fun `initial state should have non-empty currency list`() = runTest(testDispatcher) {
        val currencyList = viewModel.uiState.value.currencyList
        assertTrue(currencyList.isNotEmpty()) // Check that the list is not empty
    }

    @Test
    fun `currency list should contain specific currencies`() = runTest(testDispatcher) {
        val currencyList = viewModel.uiState.value.currencyList
        val expectedCurrencies = listOf("USD", "KRW", "JPY", "EUR", "GBP", "AUD", "CAD")
        val actualCurrencies = currencyList.map { it.code }
        assertTrue(actualCurrencies.containsAll(expectedCurrencies)) // Check that all expected currencies are present
    }
}
