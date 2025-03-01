package com.jatinkabra.music.player.currencyconvertor.domain.repo

import com.jatinkabra.music.player.currencyconvertor.domain.model.NetworkResponse
import com.jatinkabra.music.player.currencyconvertor.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface RateRepo {
    suspend fun getRateList() : Flow<NetworkResponse<List<Rate>>>
}