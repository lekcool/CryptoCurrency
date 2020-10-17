package com.lkdev.cryptocurrency.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lkdev.cryptocurrency.R
import kotlinx.android.synthetic.main.item_state.view.*

class ItemLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<StateViewHolder>() {

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        val itemView = holder.itemView

        itemView.retryButton.setOnClickListener {
            retry.invoke()
        }

        if (loadState is LoadState.Error) {
            itemView.msgErrorText.text = loadState.error.localizedMessage
        }

        itemView.progressBar.isVisible = loadState is LoadState.Loading
        itemView.retryButton.isVisible = loadState !is LoadState.Loading
        itemView.msgErrorText.isVisible = loadState !is LoadState.Loading
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return StateViewHolder(view)
    }

}

class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)