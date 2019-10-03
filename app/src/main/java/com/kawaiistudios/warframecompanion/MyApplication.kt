package com.kawaiistudios.warframecompanion

import android.app.Activity
import android.content.BroadcastReceiver
import androidx.multidex.MultiDexApplication
import com.kawaiistudios.warframecompanion.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import javax.inject.Inject

class MyApplication : MultiDexApplication(), HasActivityInjector, HasBroadcastReceiverInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorBroadcastReceiver: DispatchingAndroidInjector<BroadcastReceiver>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> = dispatchingAndroidInjectorBroadcastReceiver

}