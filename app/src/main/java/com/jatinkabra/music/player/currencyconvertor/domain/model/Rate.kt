package com.jatinkabra.music.player.currencyconvertor.domain.model

import com.jatinkabra.music.player.currencyconvertor.data.local.RateEntity

data class Rate(
    val id : String,
    val currency : String,
    val rate : Double
)


