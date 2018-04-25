package com.kawaiistudios.warframecompanion.activity.container

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View

interface IContainerPresenter {

    fun openFragment(manager: FragmentManager, container: View, fragment: Fragment)
}