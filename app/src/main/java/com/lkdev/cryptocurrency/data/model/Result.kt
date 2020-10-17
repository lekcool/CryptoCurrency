package com.lkdev.cryptocurrency.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("status")
    val status: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Data
) {
    fun isSuccess() = status == "success"
}

data class Data(
    @SerializedName("stats")
    val stats: Status,

    @SerializedName("coins")
    val coins: List<Coin>
)

data class Status(
    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,
)