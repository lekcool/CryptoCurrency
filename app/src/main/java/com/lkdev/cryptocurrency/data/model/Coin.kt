package com.lkdev.cryptocurrency.data.model

import android.text.Html
import android.text.Spanned
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

typealias CoinId = Int

data class Coin(
    @SerializedName("id")
    val id: CoinId,

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("iconUrl")
    val iconUrl: String,
) {

    fun getDes(): Spanned? {
        return when {
            description == null -> return null
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N -> {
                Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
            }
            else -> {
                Html.fromHtml(description)
            }
        }
    }

    companion object {
        val ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.name == newItem.name
                        && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem == newItem
            }
        }
    }
}