package com.kawaiistudios.warframecompanion.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseView : Fragment() {

    protected lateinit var disposable: CompositeDisposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable = CompositeDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

}