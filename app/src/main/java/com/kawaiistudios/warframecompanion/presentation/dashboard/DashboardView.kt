package com.kawaiistudios.warframecompanion.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.presentation.BaseView
import com.kawaiistudios.warframecompanion.util.injection.Injectable
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_dashboard.*
import kotlinx.android.synthetic.main.view_fissures.*
import java.lang.Exception
import javax.inject.Inject

class DashboardView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DashboardViewModel

    private val picasso = Picasso.get()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DashboardViewModel::class.java)

        disposable.addAll(
                viewModel.openNewsList.subscribe { navigateToNewsList() },
                viewModel.news.subscribe(::showNews, ::showNewsError)
        )

        txtShowAllNews.setOnClickListener { viewModel.showAllNewsClicked() }
    }

    private fun navigateToNewsList() {
        findNavController().navigate(R.id.dashboardToNews)
    }

    private fun showNews(news: DashboardNewsModel) {
//        itemView.cvContainer.setOnClickListener { callback.onClicked(item) }
        txtNewsTitle.text = news.message
        picasso.load(news.imageLink)
                .resize(600, 600)
                .centerInside()
                .into(imgNews, object : Callback {
                    override fun onError(e: Exception?) { }
                    override fun onSuccess() {
                        cvNews.visibility = View.VISIBLE
                        txtShowAllNews.visibility = View.VISIBLE
                        pbNews.visibility = View.GONE
                    }
                })
    }

    private fun showNewsError(throwable: Throwable) {
        Log.d("Warframe", throwable.message, throwable)
    }

}