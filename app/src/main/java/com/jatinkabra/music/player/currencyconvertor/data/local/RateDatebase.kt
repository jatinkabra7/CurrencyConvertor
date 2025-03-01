package com.jatinkabra.music.player.currencyconvertor.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RateEntity::class], version = 1)
abstract class RateDatabase : RoomDatabase() {

    abstract val dao : RateDao

}