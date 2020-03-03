package com.kawaiistudios.warframecompanion.data.web.market

import com.kawaiistudios.warframecompanion.data.web.market.resource.WarframeMarketItemsResponse
import com.kawaiistudios.warframecompanion.data.web.market.resource.WarframeMarketOrdersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WarframeMarketApi {

    @Headers(
            "Platform: pc",
            "Language: en"
    )
    @GET("v1/items")
    fun getItems(): Single<WarframeMarketPayload<WarframeMarketItemsResponse>>

    @Headers(
            "Platform: pc",
            "Language: en"
    )
    @GET("v1/items/{id}/orders")
    fun getItemOrders(
            @Path("id") itemUrlName: String
    ): Single<WarframeMarketPayload<WarframeMarketOrdersResponse>>

}