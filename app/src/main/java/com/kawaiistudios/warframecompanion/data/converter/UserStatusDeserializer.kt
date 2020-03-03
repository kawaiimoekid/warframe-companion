package com.kawaiistudios.warframecompanion.data.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.kawaiistudios.warframecompanion.data.web.market.resource.UserStatus
import java.lang.reflect.Type

class UserStatusDeserializer : JsonDeserializer<UserStatus> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): UserStatus {
        return when (json?.asString) {
            "ingame" -> UserStatus.InGame
            "online" -> UserStatus.Online
            else -> UserStatus.Offline
        }
    }

}