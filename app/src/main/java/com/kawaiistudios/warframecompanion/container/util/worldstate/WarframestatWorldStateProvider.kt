package com.kawaiistudios.warframecompanion.container.util.worldstate

import com.kawaiistudios.warframecompanion.container.util.Platform
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object WarframestatWorldStateProvider : IWorldStateProvider {

    private var mPcWorldState: WorldState? = null
    private var mPs4WorldState: WorldState? = null
    private var mXboxWorldState: WorldState? = null

    private var mWorldStateDownloadedCallback: WorldStateDownloaded? = null
    private var mNewsDownloadedCallback: NewsDownloaded? = null

    private var mDownloading: Boolean = false
    private val mApi by lazy {
        Retrofit.Builder()
                .baseUrl("https://ws.warframestat.us/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IWarframestatWorldStateApi::class.java)
    }
    private val mApiCallback by lazy {
        object : Callback<WorldState> {
            override fun onFailure(call: Call<WorldState>?, t: Throwable?) {
                mDownloading = false
                mWorldStateDownloadedCallback?.onFailure(t)
                mNewsDownloadedCallback?.onFailure(t)
            }

            override fun onResponse(call: Call<WorldState>?, response: Response<WorldState>?) {
                mDownloading = false
                mWorldStateDownloadedCallback?.onSuccess(response?.body())
                mNewsDownloadedCallback?.onSuccess(response?.body()?.news)
            }
        }
    }

    override fun getWorldState(platform: Platform): WorldState {
        return when (platform) {
            Platform.Pc -> {
                if (mPcWorldState != null) mPcWorldState!!
                else throw WorldStateException("Not ready")
            }
            Platform.Ps4 -> {
                if (mPcWorldState != null) mPs4WorldState!!
                else throw WorldStateException("Not ready")
            }
            Platform.Xbox -> {
                if (mPcWorldState != null) mXboxWorldState!!
                else throw WorldStateException("Not ready")
            }
        }
    }

    override fun isDownloading(): Boolean {
        return mDownloading
    }

    override fun isDownloaded(platform: Platform): Boolean {
        return when (platform) {
            Platform.Pc -> mPcWorldState != null
            Platform.Ps4 -> mPs4WorldState != null
            Platform.Xbox -> mXboxWorldState != null
        }
    }

    override fun downloadWorldState(platform: Platform) {
        mDownloading = true

        when (platform) {
            Platform.Pc -> {
                val worldState = mApi.getPcWorldState()
                worldState.enqueue(mApiCallback)
            }
            Platform.Ps4 -> {
                val worldState = mApi.getPs4WorldState()
                worldState.enqueue(mApiCallback)
            }
            Platform.Xbox -> {
                val worldState = mApi.getXboxWorldState()
                worldState.enqueue(mApiCallback)
            }
        }
    }

    override fun setOnWorldStateDownloadedCallback(callback: WorldStateDownloaded) {
        mWorldStateDownloadedCallback = callback
    }

    override fun setOnNewsDownloadedCallback(callback: NewsDownloaded) {
        mNewsDownloadedCallback = callback
    }

    interface IWarframestatWorldStateApi {
        @GET("pc")
        fun getPcWorldState(): Call<WorldState>
        @GET("ps4")
        fun getPs4WorldState(): Call<WorldState>
        @GET("xb1")
        fun getXboxWorldState(): Call<WorldState>
    }
}