package com.example.currencyconverter.currencyConverterMainFunction.di

import com.example.currencyconverter.currencyConverterMainFunction.ui.CurrencyRepository
import com.example.currencyconverter.currencyConverterMainFunction.ui.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyModule {

    @Binds
    abstract fun bindCurrencyRepository(
        impl: CurrencyRepositoryImpl
    ): CurrencyRepository


}