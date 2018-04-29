package com.kawaiistudios.warframecompanion.container.home

import com.kawaiistudios.warframecompanion.container.util.Platform
import com.kawaiistudios.warframecompanion.container.util.worldstate.IWorldStateProvider
import com.kawaiistudios.warframecompanion.container.util.worldstate.WarframestatWorldStateProvider

class HomeContainerPresenterImpl(
        private var mView: IHomeContainerView
) : IHomeContainerPresenter {

    private val mWsProvider: IWorldStateProvider by lazy { WarframestatWorldStateProvider }

    override fun onEnter() {
        if (!mWsProvider.isDownloaded(Platform.Pc))
            mWsProvider.downloadWorldState(Platform.Pc)
    }

    override fun onExit() {

    }

}