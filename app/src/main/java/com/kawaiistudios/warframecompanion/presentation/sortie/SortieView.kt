package com.kawaiistudios.warframecompanion.presentation.sortie

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog

import com.kawaiistudios.warframecompanion.R
import com.kawaiistudios.warframecompanion.di.Injectable
import kotlinx.android.synthetic.main.view_sortie.*
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import javax.inject.Inject

class SortieView : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SortieViewModel

    private var timer: CountDownTimer? = null
    private val periodFormatter = PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .appendHours()
            .appendSuffix("h ")
            .appendMinutes()
            .appendSuffix("m ")
            .appendSeconds()
            .appendSuffix("s")
            .toFormatter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_sortie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SortieViewModel::class.java)

        viewModel.sortie.observe(viewLifecycleOwner, Observer { displaySortieInfo(it) })
        viewModel.showSortie.observe(viewLifecycleOwner, Observer { showSortieViews(it) })
        viewModel.showFailure.observe(viewLifecycleOwner, Observer { showFailure(it) })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })

        btnReload.setOnClickListener { viewModel.refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        timer = null
    }

    private fun displaySortieInfo(sortie: SortieModel) {
        txtBoss.text = sortie.boss
        txtFaction.text = sortie.factionName
        imgFaction.setImageResource(sortie.factionIcon)

        txtMissionType1.text = sortie.variants[0].missionType
        txtMissionNode1.text = sortie.variants[0].node
        txtMissionModifier1.text = sortie.variants[0].modifier
        btnMissionInfo1.setOnClickListener { showModifierDetailsDialog(sortie.variants[0].modifierDescription) }

        txtMissionType2.text = sortie.variants[1].missionType
        txtMissionNode2.text = sortie.variants[1].node
        txtMissionModifier2.text = sortie.variants[1].modifier
        btnMissionInfo2.setOnClickListener { showModifierDetailsDialog(sortie.variants[1].modifierDescription) }

        txtMissionType3.text = sortie.variants[2].missionType
        txtMissionNode3.text = sortie.variants[2].node
        txtMissionModifier3.text = sortie.variants[2].modifier
        btnMissionInfo3.setOnClickListener { showModifierDetailsDialog(sortie.variants[2].modifierDescription) }

        if (sortie.millisUntilExpiry > 0) {
//            showCountdown()
            startTimer(sortie.millisUntilExpiry)
        } else {
//            showTimeExpired()
        }
    }

    private fun showSortieViews(show: Boolean) {
        scroll.visibility = if (show) VISIBLE else GONE
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

    private fun showModifierDetailsDialog(details: String) {
        context?.let { ctx ->
            MaterialDialog.Builder(ctx)
                    .content(details)
                    .positiveText(R.string.close)
                    .show()
        }
    }

    private fun startTimer(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1000) {
            override fun onFinish() {
//                showTimeExpired()
            }

            override fun onTick(millis: Long) {
                txtTimeLeft.text = periodFormatter.print(Duration.millis(millis).toPeriod())
            }
        }

        timer?.start()
    }

    /*private fun showCountdown() {
        itemView.txtTimeLeft.visibility = VISIBLE
        itemView.txtTimeExpired.visibility = INVISIBLE
    }

    private fun showTimeExpired() {
        itemView.txtTimeLeft.visibility = INVISIBLE
        itemView.txtTimeExpired.visibility = VISIBLE
    }*/

}