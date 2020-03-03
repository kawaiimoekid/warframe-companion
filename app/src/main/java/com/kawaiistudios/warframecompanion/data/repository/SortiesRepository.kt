package com.kawaiistudios.warframecompanion.data.repository

import com.kawaiistudios.warframecompanion.data.web.status.WarframeStatusApi
import javax.inject.Inject

class SortiesRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    fun getSortie() = api.getSortie()

}