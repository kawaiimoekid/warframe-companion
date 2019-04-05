package com.kawaiistudios.warframecompanion.presentation.fissures

import androidx.annotation.DrawableRes

data class FissuresModel(
        val id: String,
        val node: String,
        val missionType: String,
        val enemy: String,
        val millisUntilExpiry: Long,
        @DrawableRes val tierResource: Int
)