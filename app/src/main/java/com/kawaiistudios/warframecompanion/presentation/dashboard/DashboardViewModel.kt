package com.kawaiistudios.warframecompanion.presentation.dashboard

import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.repository.NewsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
        newsRepo: NewsRepository
) : BaseViewModel() {

    val openNewsList = PublishSubject.create<Unit>()
    val news = BehaviorSubject.create<DashboardNewsModel>()


    init {
        disposable.add(
                newsRepo.getNewsSingle()
                        .subscribeOn(Schedulers.io())
                        .map { news -> news.filter { !it.translations.en.isNullOrBlank() } }
                        .map { news -> news.maxBy { it.date } }
                        .map { news -> mapNews(news) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { success -> news.onNext(success) },
                                { error -> news.onError(error) }
                        )
        )
    }


    fun showAllNewsClicked() {
        openNewsList.onNext(Unit)
    }

    private fun mapNews(news: News): DashboardNewsModel {
        return DashboardNewsModel(
                news.id,
                "[${extractTimeDifference(news.date)}] ${news.translations.en ?: ""}",
                news.forumLink,
                news.imageLink
        )
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

}