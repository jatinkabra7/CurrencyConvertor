package com.jatinkabra.music.player.currencyconvertor.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RateEntity(
    @PrimaryKey
    val id : String,
    val currency : String,
    val rate : Double
)