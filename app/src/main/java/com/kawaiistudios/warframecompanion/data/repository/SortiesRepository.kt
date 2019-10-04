package com.kawaiistudios.warframecompanion.data.repository

import com.kawaiistudios.warframecompanion.data.WarframeStatusApi
import javax.inject.Inject

class SortiesRepository @Inject constructor(
        private val api: WarframeStatusApi
) {

    fun getSortie() = api.getSortie()

}