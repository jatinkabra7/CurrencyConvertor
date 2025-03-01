package com.jatinkabra.music.player.currencyconvertor.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RateDao {

    @Upsert
    suspend fun updateRates(rateList : List<RateEntity>)

    @Query("SELECT * FROM RateEntity")
    suspend fun getAllRates() : List<RateEntity>
}