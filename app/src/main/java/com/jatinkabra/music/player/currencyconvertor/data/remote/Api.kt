package com.jatinkabra.music.player.currencyconvertor.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/v1/latest")
    suspend fun getRates(
        @Query("apikey") key : String = Constant.API_KEY
    ) : RateModel
}