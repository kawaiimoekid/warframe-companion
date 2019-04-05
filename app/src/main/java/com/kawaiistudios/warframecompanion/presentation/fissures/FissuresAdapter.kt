package com.kawaiistudios.warframecompanion.presentation.fissures

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.util.adapter.GreatAdapter
import com.kawaiistudios.warframecompanion.util.adapter.GreatViewHolder
import kotlinx.android.synthetic.main.adapter_fissures.view.*
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder

class FissuresAdapter : GreatAdapter<FissuresModel>() {

    private val periodFormatter = PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .appendHours()
            .appendSuffix("h ")
            .appendMinutes()
            .appendSuffix("m ")
            .appendSeconds()
            .appendSuffix("s")
            .toFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = FissuresViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_fissures, parent, false))

    inner class FissuresViewHolder(view: View)
        : GreatViewHolder<FissuresModel>(view) {

        private var timer: CountDownTimer? = null

        override fun bind(item: FissuresModel) {
            itemView.txtMissionType.text = item.missionType
            itemView.txtNode.text = item.node
            itemView.txtEnemy.text = item.enemy
            itemView.imgTier.setImageResource(item.tierResource)
            if (item.millisUntilExpiry > 0) {
                showCountdown()
                startTimer(item.millisUntilExpiry)
            } else {
                showTimeExpired()
            }
        }

        private fun startTimer(millis: Long) {
            timer?.cancel()
            timer = object : CountDownTimer(millis, 1000) {
                override fun onFinish() {
                    showTimeExpired()
                }

                override fun onTick(millis: Long) {
                    itemView.txtTimeLeft.text = periodFormatter.print(Duration.millis(millis).toPeriod())
                }
            }

            timer?.start()
        }

        private fun showCountdown() {
            itemView.txtTimeLeft.visibility = VISIBLE
            itemView.txtTimeExpired.visibility = INVISIBLE
        }

        private fun showTimeExpired() {
            itemView.txtTimeLeft.visibility = INVISIBLE
            itemView.txtTimeExpired.visibility = VISIBLE
        }

    }

}