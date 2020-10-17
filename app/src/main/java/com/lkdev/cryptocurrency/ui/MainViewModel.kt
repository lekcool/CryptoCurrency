package com.lkdev.cryptocurrency.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.lkdev.cryptocurrency.data.CoinRepository
import com.lkdev.cryptocurrency.data.model.Coin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    private val _listCoin = MediatorLiveData<PagingData<Coin>>()
    val listCoin: LiveData<PagingData<Coin>>
        get() = _listCoin

    fun fetchListCoin(prefix: String?) {
        viewModelScope.launch {
            repository.listCoin(prefix).collectLatest {
                _listCoin.value = it
            }
        }
    }
}