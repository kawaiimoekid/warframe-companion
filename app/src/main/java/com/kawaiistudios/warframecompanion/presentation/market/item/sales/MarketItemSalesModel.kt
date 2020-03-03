package com.kawaiistudios.warframecompanion.presentation.market.item.sales

import com.kawaiistudios.warframecompanion.data.web.market.resource.UserStatus

data class MarketItemSalesModel(
        val id: String,
        val userName: String,
        val platinum: Int,
        val quantity: Int,
        val userStatus: UserStatus
)