package com.kawaiistudios.warframecompanion.presentation.dashboard.widget.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.di.Injectable
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.view_dashboard_news_widget.*
import javax.inject.Inject

class DashboardNewsWidgetView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardNewsWidgetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard_news_widget, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardNewsWidgetViewModel::class.java)

        disposable.add(viewModel.news.subscribe(::bind, ::showSomeError))

        btnShowAllNews.setOnClickListener { findNavController().navigate(R.id.dashboardToNews) }
    }

    private fun bind(news: DashboardNewsWidgetModel) {
        cvNews.setOnClickListener { showForumDialog(news.forumLink) }
        txtNewsTitle.text = news.message
        Picasso.get()
                .load(news.imageLink)
                .resize(600, 600)
                .centerInside()
                .into(imgNews)
    }

    private fun showForumDialog(link: String) {
        context?.let { ctx ->
            MaterialDialog.Builder(ctx)
                    .content(R.string.view_news_dialog_view_post_content)
                    .positiveText(R.string.view)
                    .onPositive { _, _ -> openUri(link) }
                    .negativeText(R.string.close)
                    .show()
        }
    }

    private fun openUri(link: String) {
        context?.let { ctx ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ctx.startActivity(intent)
        }
    }

    private fun showSomeError(throwable: Throwable) {
        Toast.makeText(context!!, "Some error has occured", Toast.LENGTH_SHORT).show()
    }

}