package com.kawaiistudios.warframecompanion.container.home.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.IContainerView
import kotlinx.android.synthetic.main.fragment_news.*

class NewsViewImpl : Fragment(), INewsView {

    private val mContainerView: IContainerView by lazy { context as IContainerView }
    private val mPresenter by lazy { NewsPresenterImpl(this) }
    private val mAdapter by lazy { NewsAdapter(context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNews.layoutManager = LinearLayoutManager(context)
        rvNews.adapter = mAdapter

        mPresenter.getNews()
    }

    override fun addEvent(event: News.Event) {
        mAdapter.addItem(event)
        if (mAdapter.itemCount > 0)
            pbLoading.visibility = View.GONE
    }

    override fun showToast(text: String) {
        mContainerView.showToast(text)
    }

    companion object {
        fun newInstance(): NewsViewImpl = NewsViewImpl()
    }
}