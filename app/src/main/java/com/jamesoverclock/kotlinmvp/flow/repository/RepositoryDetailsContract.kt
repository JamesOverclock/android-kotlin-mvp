package com.jamesoverclock.kotlinmvp.flow.repository

import com.jamesoverclock.kotlinmvp.model.RepositoryDetail
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpPresenter
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpView

object RepositoryDetailsContract {

    interface View : BaseMvpView {
        fun showRepository(repositoryDetail: RepositoryDetail)

        fun showReloadButton()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadRepository(name: String)
    }
}