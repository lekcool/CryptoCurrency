package com.lkdev.cryptocurrency.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.lkdev.cryptocurrency.data.model.Coin
import com.lkdev.cryptocurrency.network.CryptoCurrencyService

class CoinPagingSource(
    private val service: CryptoCurrencyService,
    private val prefix: String?
) : PagingSource<Int, Coin>() {

    companion object {
        fun createSource(service: CryptoCurrencyService, prefix: String?, limit: Int): Pager<Int, Coin> {
            val config = PagingConfig(limit)
            return Pager(config) { CoinPagingSource(service, prefix) }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            var nextKey: Int? = params.key

            val response = service.listCoin(prefix, nextKey ?: 0, params.loadSize)
            if (!response.isSuccess()) {
                return LoadResult.Error(Exception("type: ${response.type}, message: ${response.message}"))
            }

            nextKey = response.data.stats.offset + params.loadSize
            if (nextKey >= response.data.stats.total) {
                nextKey = null
            }

            LoadResult.Page(response.data.coins, null, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}