package com.kawaiistudios.warframecompanion.presentation.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.di.Injectable
import kotlinx.android.synthetic.main.view_news.*
import javax.inject.Inject

class NewsView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: NewsViewModel

    private val adapter = NewsAdapter(::onNewsClicked)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewsViewModel::class.java)

        rvNews.adapter = adapter

        disposable.addAll(
                viewModel.news.subscribe(adapter::update),
                viewModel.showLoading.subscribe(::showLoading),
                viewModel.showFailure.subscribe(::showFailure)
        )

        btnReload.setOnClickListener { viewModel.refresh() }
    }

    private fun onNewsClicked(news: NewsModel) {
        context?.let { ctx ->
            MaterialDialog.Builder(ctx)
                    .content(R.string.view_news_dialog_view_post_content)
                    .positiveText(R.string.view)
                    .onPositive { _, _ ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.forumLink))
                        ctx.startActivity(intent)
                    }
                    .negativeText(R.string.close)
                    .show()
        }
    }

    private fun displayNews(news: List<NewsModel>) {
        adapter.update(news)
    }

    private fun showFailure(show: Boolean) {
        if (show) {
            txtFailure.visibility = VISIBLE
            btnReload.visibility = VISIBLE
        } else {
            txtFailure.visibility = INVISIBLE
            btnReload.visibility = INVISIBLE
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            pbLoading.visibility = VISIBLE
        } else {
            pbLoading.visibility = GONE
        }
    }

}