package com.lkdev.cryptocurrency.ui

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.lkdev.cryptocurrency.data.model.Coin
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Coin?) {
        item?.let {
            itemView.iconImage.load(it.iconUrl, ImageLoader.Builder(itemView.context)
                .componentRegistry {
                    add(SvgDecoder(itemView.context))
                }
                .build())
            itemView.titleText.text = it.name
            itemView.descriptionText.text = it.getDes()
        }
    }
}