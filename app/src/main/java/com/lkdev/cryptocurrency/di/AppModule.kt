package com.lkdev.cryptocurrency.di

import com.lkdev.cryptocurrency.data.CoinRepository
import com.lkdev.cryptocurrency.network.Remote
import com.lkdev.cryptocurrency.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            repository = get()
        )
    }
    single {
        CoinRepository(
            service = get()
        )
    }
    single {
        Remote.createService()
    }
}