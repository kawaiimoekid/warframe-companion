package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.sortie

import androidx.annotation.DrawableRes

data class DashboardSortieWidgetModel(
        val millisUntilExpiry: Long,
        val factionName: String,
        @DrawableRes val factionIcon: Int,
        val missionType1: String,
        val missionModifier1: String,
        val missionType2: String,
        val missionModifier2: String,
        val missionType3: String,
        val missionModifier3: String
)