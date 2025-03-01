package com.jatinkabra.music.player.currencyconvertor.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jatinkabra.music.player.currencyconvertor.di.AppModule
import com.jatinkabra.music.player.currencyconvertor.domain.repo.RateRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import com.jatinkabra.music.player.currencyconvertor.domain.model.Rate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
    private val repo : RateRepo
) : ViewModel() {

    private val _rateList = MutableStateFlow<List<Rate>>(emptyList())
    val rateList = _rateList.asStateFlow()

    private suspend fun getRate() {
        val list : MutableList<Rate> = mutableListOf()
        repo.getRateList().collectLatest { it ->
            it.data?.map {
                list.add(
                    Rate(it.id, it.currency, it.rate)
                )

            }
        }
        Log.d("itemAdded", "function called")


        _rateList.value = list
    }

    init {
        viewModelScope.launch {
            getRate()
        }
    }


}