package com.kawaiistudios.warframecompanion.presentation.sortie

import androidx.annotation.DrawableRes

data class SortieModel(
        val id: String,
        val millisUntilExpiry: Long,
        val boss: String,
        val factionName: String,
        @DrawableRes val factionIcon: Int,
        val variants: List<SortieVariantModel>
)