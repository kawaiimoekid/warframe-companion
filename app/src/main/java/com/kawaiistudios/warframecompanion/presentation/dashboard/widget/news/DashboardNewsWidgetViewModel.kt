package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news

import com.kawaiistudios.warframecompanion.data.model.News
import com.kawaiistudios.warframecompanion.data.repository.NewsRepository
import com.kawaiistudios.warframecompanion.presentation.BaseViewModel
import com.kawaiistudios.warframecompanion.util.extension.toPrettyTimeDifference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DashboardNewsWidgetViewModel @Inject constructor(
        newsRepo: NewsRepository
) : BaseViewModel() {

    val news = BehaviorSubject.create<DashboardNewsWidgetModel>()

    init {
        disposable.add(
                newsRepo.getTopNews()
                        .subscribeOn(Schedulers.io())
                        .map(::mapNews)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(news::onNext, news::onError)
        )
    }

    private fun mapNews(news: News) = DashboardNewsWidgetModel(
            news.id,
            "[${news.date.toPrettyTimeDifference()}] ${news.translations.en ?: ""}",
            news.forumLink,
            news.imageLink
    )

}