package com.kawaiistudios.warframecompanion.presentation.market.item.buys

import com.kawaiistudios.warframecompanion.data.web.market.resource.UserStatus

data class MarketItemBuysModel(
        val id: String,
        val userName: String,
        val platinum: Int,
        val quantity: Int,
        val userStatus: UserStatus
)