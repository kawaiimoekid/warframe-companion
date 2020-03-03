package com.kawaiistudios.warframecompanion.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kawaiistudios.warframecompanion.data.converter.DateTimeDeserializer
import com.kawaiistudios.warframecompanion.data.converter.UserStatusDeserializer
import com.kawaiistudios.warframecompanion.data.db.Database
import com.kawaiistudios.warframecompanion.data.web.market.WarframeMarketApi
import com.kawaiistudios.warframecompanion.data.web.market.resource.UserStatus
import com.kawaiistudios.warframecompanion.data.web.status.WarframeStatusApi
import dagger.Module
import dagger.Provides
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideWarframeStatusApi(): WarframeStatusApi = Retrofit.Builder()
            .baseUrl("https://api.warframestat.us/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .registerTypeAdapter(DateTime::class.java, DateTimeDeserializer())
                    .create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WarframeStatusApi::class.java)

    @Singleton
    @Provides
    fun provideWarframeMarketApi(): WarframeMarketApi = Retrofit.Builder()
            .baseUrl("https://api.warframe.market/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .registerTypeAdapter(DateTime::class.java, DateTimeDeserializer())
                    .registerTypeAdapter(UserStatus::class.java, UserStatusDeserializer())
                    .create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WarframeMarketApi::class.java)



    @Provides
    fun provideSharedPrefs(app: Application): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun provideDb(app: Application) =
            Room.databaseBuilder(app, Database::class.java, "wfcompanion.db")
                    .fallbackToDestructiveMigration()
                    .build()



}