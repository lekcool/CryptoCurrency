package com.lkdev.cryptocurrency.network

import com.lkdev.cryptocurrency.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyService {

    @GET("coins")
    suspend fun listCoin(
        @Query("prefix") prefix: String? = null,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): Result
}