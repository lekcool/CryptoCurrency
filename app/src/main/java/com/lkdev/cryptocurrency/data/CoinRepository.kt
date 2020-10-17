package com.lkdev.cryptocurrency.data

import androidx.paging.PagingData
import com.lkdev.cryptocurrency.data.model.Coin
import com.lkdev.cryptocurrency.network.CryptoCurrencyService
import kotlinx.coroutines.flow.Flow

class CoinRepository(private val service: CryptoCurrencyService) {

    fun listCoin(prefix: String?): Flow<PagingData<Coin>> {
        return CoinPagingSource.createSource(service, prefix, 10).flow
    }
}