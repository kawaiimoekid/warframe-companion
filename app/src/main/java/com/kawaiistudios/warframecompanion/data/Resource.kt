package com.kawaiistudios.warframecompanion.data

data class Resource<T>(
        val status: Status,
        val data: T?,
        val message: String?
) {

    companion object {

        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)
        fun <T> error(data: T?, msg: String) = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?) = Resource(Status.LOADING, data, null)

    }

}