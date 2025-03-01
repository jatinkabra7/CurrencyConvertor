package com.jatinkabra.music.player.currencyconvertor.data

import com.jatinkabra.music.player.currencyconvertor.data.local.RateDao
import com.jatinkabra.music.player.currencyconvertor.data.local.toRate
import com.jatinkabra.music.player.currencyconvertor.data.local.toRateEntity
import com.jatinkabra.music.player.currencyconvertor.data.local.toRateList
import com.jatinkabra.music.player.currencyconvertor.data.remote.Api
import com.jatinkabra.music.player.currencyconvertor.data.remote.Constant
import com.jatinkabra.music.player.currencyconvertor.domain.model.NetworkResponse
import com.jatinkabra.music.player.currencyconvertor.domain.model.Rate
import com.jatinkabra.music.player.currencyconvertor.domain.repo.RateRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class RateRepoImpl(
    private val api : Api,
    private val dao: RateDao
) : RateRepo {

    // get list from api
    // store it in room database
    // fetch from room database

    private suspend fun getRateFromApi() : List<Rate> {
        val response = api.getRates(Constant.API_KEY)
        return response.toRateList()
    }

    private suspend fun upsertLocalRates(rateList : List<Rate>) {
        dao.updateRates(rateList.map { it.toRateEntity() })
    }

    private suspend fun getLocalRates() : List<Rate> {
        return dao.getAllRates().map { it.toRate() }
    }


    override suspend fun getRateList(): Flow<NetworkResponse<List<Rate>>> = flow {
        val localCurrencyRates = getLocalRates()
        emit(NetworkResponse.Success(localCurrencyRates))

        try {
            val newRates = getRateFromApi()
            upsertLocalRates(newRates)
            emit(NetworkResponse.Success(newRates))
        } catch (e: IOException) {
            emit(
                NetworkResponse.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = localCurrencyRates
                )
            )
        } catch (e: Exception) {
            emit(
                NetworkResponse.Error(
                    message = "Oops, something went wrong! ${e.message}",
                    data = localCurrencyRates
                )
            )
        }

    }



}