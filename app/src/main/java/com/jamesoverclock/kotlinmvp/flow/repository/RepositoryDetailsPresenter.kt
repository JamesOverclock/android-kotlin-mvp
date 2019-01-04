package com.jamesoverclock.kotlinmvp.flow.repository

import com.jamesoverclock.kotlinmvp.api.GeneralErrorHandler
import com.jamesoverclock.kotlinmvp.manager.ApiManager
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

class RepositoryDetailsPresenter : BaseMvpPresenterImpl<RepositoryDetailsContract.View>(),
        RepositoryDetailsContract.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "Yalantis"
    }

    override fun loadRepository(name: String) {
        ApiManager.loadRepository(ORGANIZATION_NAME, name)
                .subscribe(Action1 { mView?.showRepository(it) },
                        GeneralErrorHandler(mView, true,
                                { throwable, errorBody, isNetwork -> mView?.showReloadButton() }))
    }
}