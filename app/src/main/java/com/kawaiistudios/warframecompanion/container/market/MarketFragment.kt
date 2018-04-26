package com.kawaiistudios.warframecompanion.container.market


import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.container.IContainerView
import kotlinx.android.synthetic.main.fragment_market.*

class MarketFragment : Fragment() {

    private val mContainerView: IContainerView by lazy { context as IContainerView }
    private var mCurrentColor: Int = Color.parseColor("#444444")
    private val mColors: IntArray = arrayOf(
            Color.parseColor("#CD7F32"),
            Color.parseColor("#A39375"),
            Color.parseColor("#C0C0C0"),
            Color.parseColor("#D4AF37")
    ).toIntArray()
    private var mSelectedIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_market, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClickMe.setOnClickListener {
            setBackgroundGradientColor(mColors[mSelectedIndex++ % 4])
        }
    }

    private fun setBackgroundGradientColor(color: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), mCurrentColor, color)
        colorAnimation.duration = 250
        colorAnimation.addUpdateListener { animation ->
            val colors = arrayOf(animation.animatedValue as Int, Color.parseColor("#444444"))
            val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors.toIntArray())
            gd.cornerRadius = 0f

            layoutRoot?.background = gd
        }

        colorAnimation.start()
    }

    companion object {
        fun newInstance(): MarketFragment = MarketFragment()
    }
}