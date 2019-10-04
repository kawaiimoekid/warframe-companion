package com.kawaiistudios.warframecompanion.presentation.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import com.kawaiistudios.warframecompanion.presentation.BaseView
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
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(NewsViewModel::class.java)
        disposable.addAll(
                viewModel.news.subscribe(adapter::update),
                viewModel.showLoading.subscribe { pbLoading.visibility = if (it) VISIBLE else GONE },
                viewModel.showFailure.subscribe { layoutError.visibility = if (it) VISIBLE else INVISIBLE }
        )

        rvNews.adapter = adapter
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

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

}