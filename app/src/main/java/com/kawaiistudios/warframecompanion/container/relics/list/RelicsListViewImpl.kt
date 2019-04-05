package com.kawaiistudios.warframecompanion.container.relics.list


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import kotlinx.android.synthetic.main.fragment_relics_list.*

private const val ARG_RELIC_TYPE = "relicType"

class RelicsListViewImpl : Fragment(), IRelicsListView {

    private var mRelicType: Int? = null
    private val mPresenter: IRelicsListPresenter by lazy { RelicsListPresenterImpl(this) }
    private val mAdapter by lazy { RelicsListAdapter(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mRelicType = it.getInt(ARG_RELIC_TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_relics_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRelics.layoutManager = LinearLayoutManager(context)
        rvRelics.adapter = mAdapter

        mRelicType?.let { mPresenter.getRelics(it) }
    }

    override fun addRelic(relic: Pair<String, Int>) {
        mAdapter.addItem(relic)
        if (mAdapter.itemCount > 0)
            pbLoading?.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(relicType: Int) =
                RelicsListViewImpl().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_RELIC_TYPE, relicType)
                    }
                }
    }
}
