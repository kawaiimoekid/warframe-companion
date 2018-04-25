package com.kawaiistudios.warframecompanion.activity.container

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View


class ContainerPresenterImpl : IContainerPresenter {

    override fun openFragment(manager: FragmentManager, container: View, fragment: Fragment) {
        manager.beginTransaction().replace(container.id, fragment).commit()
    }
}