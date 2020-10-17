package com.lkdev.cryptocurrency.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkdev.cryptocurrency.R
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()
    private val mainAdapter = MainAdapter()

    private val itemLoadStateAdapter = ItemLoadStateAdapter { mainAdapter.retry() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.fetchListCoin(null)
    }

    private fun init() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.fetchListCoin(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.fetchListCoin(null)
                }
                return true
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            mainAdapter.refresh()
        }

        retryButton.setOnClickListener {
            mainAdapter.refresh()
        }

        recyclerView.apply {
            setHasFixedSize(true)
            adapter = mainAdapter.withLoadStateFooter(itemLoadStateAdapter)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        mainAdapter.addLoadStateListener {
            val loadState = it.refresh
            if (loadState is LoadState.Error) {
                msgErrorText.text = loadState.error.localizedMessage
            }

            swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            msgErrorText.isVisible = loadState is LoadState.Error
        }

        viewModel.listCoin.observe(viewLifecycleOwner)  {
            mainAdapter.submitData(lifecycle, it)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}