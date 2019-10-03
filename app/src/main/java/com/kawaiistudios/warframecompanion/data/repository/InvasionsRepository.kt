package com.kawaiistudios.warframecompanion.data.repository

import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import javax.inject.Inject

class InvasionsRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    fun getInvasions() = api.getInvasions()

}