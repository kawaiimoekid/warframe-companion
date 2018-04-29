package com.kawaiistudios.warframecompanion.container.relics.list

import com.google.firebase.firestore.FirebaseFirestore

class RelicsListPresenterImpl(
        private val mView: IRelicsListView
) : IRelicsListPresenter {

    private val mDatabase by lazy { FirebaseFirestore.getInstance() }

    override fun getRelics(type: Int) {
        mDatabase.collection("relics")
                .whereEqualTo("type", type)
                .get()
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) return@addOnCompleteListener

                    val relics = task.result.map { Pair(it["name"].toString(), type) }
                    relics.sortedBy { it.first }.forEach { mView.addRelic(it) }
                }
    }

}