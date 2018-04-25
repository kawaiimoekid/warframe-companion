package com.kawaiistudios.warframecompanion.fragment.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.activity.container.IContainerActivity

class NewsFragment : Fragment() {

    private lateinit var mContainerActivity: IContainerActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContainerActivity = context as IContainerActivity
        mContainerActivity.setToolbarTitle("News")
    }

    companion object {
        fun newInstance(): NewsFragment = NewsFragment()
    }
}