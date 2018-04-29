package com.kawaiistudios.warframecompanion.container.relics


import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.IContainerView
import kotlinx.android.synthetic.main.fragment_relics_container.*

class RelicsContainerViewImpl : Fragment(), IRelicsContainerView {

    private val mContainerView: IContainerView by lazy { context as IContainerView }
    private val mAdapter by lazy { RelicsContainerPagerAdapter(this) }
    private var mCurrentColor: Int = Color.parseColor("#444444")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_relics_container, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = mAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> setBackgroundGradient(Color.parseColor(getString(R.string.view_relics_container_background_lith)))
                    1 -> setBackgroundGradient(Color.parseColor(getString(R.string.view_relics_container_background_meso)))
                    2 -> setBackgroundGradient(Color.parseColor(getString(R.string.view_relics_container_background_neo)))
                    else -> setBackgroundGradient(Color.parseColor(getString(R.string.view_relics_container_background_axi)))
                }
            }
        })
        viewPager.offscreenPageLimit = 3
        tabLayout.setupWithViewPager(viewPager)

        setBackgroundGradient(Color.parseColor(getString(R.string.view_relics_container_background_lith)))
    }

    override fun setBackgroundGradient(color: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), mCurrentColor, color)
        colorAnimation.duration = 250
        colorAnimation.addUpdateListener { animation ->
            val colors = arrayOf(animation.animatedValue as Int, Color.parseColor("#444444"))
            val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors.toIntArray())
            gd.cornerRadius = 0f

            layoutRoot?.background = gd
        }

        colorAnimation.start()
        mCurrentColor = color
    }

    companion object {
        fun newInstance(): RelicsContainerViewImpl = RelicsContainerViewImpl()
    }
}