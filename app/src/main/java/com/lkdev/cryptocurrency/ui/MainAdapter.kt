package com.lkdev.cryptocurrency.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lkdev.cryptocurrency.R
import com.lkdev.cryptocurrency.data.model.Coin

class MainAdapter : PagingDataAdapter<Coin, RecyclerView.ViewHolder>(Coin.ITEM_DIFF_UTIL) {

    override fun getItemViewType(position: Int): Int {
        return when {
            ((position + 1) % 5) == 0 -> R.layout.item_coin_2
            else -> R.layout.item_coin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_coin -> CoinViewHolder(view)
            R.layout.item_coin_2 -> Coin2ViewHolder(view)
            else -> throw IllegalStateException("unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CoinViewHolder -> holder.bind(item)
            is Coin2ViewHolder -> holder.bind(item)
        }
    }
}