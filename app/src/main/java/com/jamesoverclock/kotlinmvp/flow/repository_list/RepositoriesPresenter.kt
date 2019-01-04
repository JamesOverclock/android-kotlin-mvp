package com.jamesoverclock.kotlinmvp.flow.repository_list

import com.jamesoverclock.kotlinmvp.api.GeneralErrorHandler
import com.jamesoverclock.kotlinmvp.manager.ApiManager
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

class RepositoriesPresenter : BaseMvpPresenterImpl<RepositoriesContract.View>(), RepositoriesContract.Preseter {

    companion object {
        private val ORGANIZATION_NAME = "Yalantis"
        private val REPOS_TYPE = "pubild"
    }

    override fun loadRepositories() {
        ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showOrganizations(it) },
                        GeneralErrorHandler(mView, true, { throwable, errorBody, isNetwork -> mView?.showError(throwable.message!!) }))
    }
}