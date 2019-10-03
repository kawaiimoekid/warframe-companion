package com.kawaiistudios.warframecompanion.presentation.news

import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.repository.NewsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import com.kawaiistudios.warframecompanion.util.extension.toPrettyTimeDifference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class NewsViewModel @Inject constructor(
        private val repo: NewsRepository
) : BaseViewModel() {

    val news = BehaviorSubject.create<List<NewsModel>>()
    val showLoading = BehaviorSubject.create<Boolean>()
    val showFailure = BehaviorSubject.create<Boolean>()

    init {
        refresh()
    }

    /**
     * Requests new data to be loaded. Not to be called on ViewModel
     * initialization as it will request the data by itself.
     */
    fun refresh() {
        showFailure.onNext(false)
        showLoading.onNext(true)
        disposable.add(
                repo.getNews()
                        .subscribeOn(Schedulers.io())
                        .map(::mapNews)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(::onSuccess, ::onFailure)
        )
    }

    private fun mapNews(resource: List<News>): List<NewsModel> {
        return resource.map {
            NewsModel(
                    it.id,
                    "[${it.date.toPrettyTimeDifference()}] ${it.translations.en ?: ""}",
                    it.forumLink,
                    it.imageLink
            )
        }
    }

    private fun onSuccess(data: List<NewsModel>) {
        showLoading.onNext(false)
        showFailure.onNext(false)
        news.onNext(data)
    }

    private fun onFailure(throwable: Throwable) {
        showLoading.onNext(false)
        showFailure.onNext(true)
    }

}