package com.jamesoverclock.kotlinmvp.flow.repository_list

import com.jamesoverclock.kotlinmvp.model.Repository
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpPresenter
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpView

object RepositoriesContract {

    interface View : BaseMvpView {
        fun showOrganizations(repositories: MutableList<Repository>)
    }

    interface Preseter : BaseMvpPresenter<View> {
        fun loadRepositories()
    }
}