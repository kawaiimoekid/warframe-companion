package com.kawaiistudios.warframecompanion.util.injection

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import com.kawaiistudios.warframecompanion.data.converter.DateTimeDeserializer
import com.kawaiistudios.warframecompanion.data.converter.LiveDataCallAdapterFactory
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
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WarframeStatusApi::class.java)

    @Provides
    fun provideSharedPrefs(app: Application) = PreferenceManager.getDefaultSharedPreferences(app)

}