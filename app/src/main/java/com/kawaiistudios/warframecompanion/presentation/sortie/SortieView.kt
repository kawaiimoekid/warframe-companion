package com.kawaiistudios.warframecompanion.presentation.sortie

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
import kotlinx.android.synthetic.main.view_sortie.*
import javax.inject.Inject

class SortieView : BaseView(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SortieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.view_sortie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(SortieViewModel::class.java)
        disposable.addAll(
                viewModel.sortie.subscribe(::bind),
                viewModel.timeLeft.subscribe { txtTimeLeft.text = it },
                viewModel.showSortie.subscribe { scroll.visibility = if (it) VISIBLE else GONE },
                viewModel.showFailure.subscribe { layoutError.visibility = if (it) VISIBLE else INVISIBLE },
                viewModel.showLoading.subscribe { pbLoading.visibility = if (it) VISIBLE else GONE }
        )

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        btnReload.setOnClickListener { viewModel.refresh() }
    }

    private fun bind(sortie: SortieModel) {
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
    }

    private fun showModifierDetailsDialog(details: String) {
        context?.let { ctx ->
            MaterialDialog.Builder(ctx)
                    .content(details)
                    .positiveText(R.string.close)
                    .show()
        }
    }

}