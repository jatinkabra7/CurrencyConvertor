package com.jatinkabra.music.player.currencyconvertor.di

import android.app.Application
import android.provider.Telephony.Mms.Rate
import androidx.room.Room
import com.jatinkabra.music.player.currencyconvertor.data.RateRepoImpl
import com.jatinkabra.music.player.currencyconvertor.data.local.RateDao
import com.jatinkabra.music.player.currencyconvertor.data.local.RateDatabase
import com.jatinkabra.music.player.currencyconvertor.data.remote.Api
import com.jatinkabra.music.player.currencyconvertor.data.remote.Constant
import com.jatinkabra.music.player.currencyconvertor.domain.repo.RateRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi() : Api {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitInstance = retrofit.create(Api::class.java)

        return retrofitInstance
    }

    @Provides
    @Singleton
    fun provideDB(application: Application) : RateDatabase {
        return Room.databaseBuilder(
            application,
            RateDatabase::class.java,
            "RateDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepo(api : Api, db : RateDatabase) : RateRepo {
        return RateRepoImpl(
            api = api,
            dao = db.dao
        )
    }
}