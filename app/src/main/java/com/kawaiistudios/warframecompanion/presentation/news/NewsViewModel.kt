package com.kawaiistudios.warframecompanion.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kawaiistudios.warframecompanion.data.FetchCallback
import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.repository.NewsRepository
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class NewsViewModel @Inject constructor(
        private val repository: NewsRepository
) : ViewModel(), FetchCallback {

    val news = Transformations.map(repository.news, ::mapNews)

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private val _showFailure = MutableLiveData<Boolean>()
    val showFailure: LiveData<Boolean> get() = _showFailure


    init {
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchNews(this)
    }

    fun refresh() {
        _showFailure.value = false
        _showLoading.value = true
        repository.fetchNews(this)
    }

    private fun mapNews(resource: List<News>?): List<NewsModel> {
        return resource?.map {
            NewsModel(
                    it.id,
                    "[${extractTimeDifference(it.date)}] ${it.translations.en ?: ""}",
                    it.forumLink,
                    it.imageLink
            )
        } ?: emptyList()
    }

    private fun extractTimeDifference(dateTime: DateTime): String {
        val difference = Duration(dateTime, DateTime.now(DateTimeZone.UTC))
        return when {
            difference.standardDays > 0 -> "${difference.standardDays}d"
            difference.standardHours > 0 -> "${difference.standardHours}h"
            difference.standardMinutes > 0 -> "${difference.standardMinutes}m"
            difference.standardSeconds > 0 -> "${difference.standardSeconds}s"
            else -> "?"
        }
    }

    override fun onFailure() {
        _showLoading.postValue(false)
        _showFailure.postValue(true)
    }

    override fun onSuccess() {
        _showLoading.postValue(false)
        _showFailure.postValue(false)
    }

}