package com.jamesoverclock.kotlinmvp.flow.repository_list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jamesoverclock.kotlinmvp.R
import com.jamesoverclock.kotlinmvp.flow.repository.RepositoryDetailActivity
import com.jamesoverclock.kotlinmvp.model.Repository
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_repositories_list.*

class RepositoriesListActivity : BaseMvpActivity<RepositoriesContract.View,
        RepositoriesPresenter>(),
        RepositoriesContract.View {

    private var mAdapter: RepositoriesAdapter? = null
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    override fun showOrganizations(repositories: MutableList<Repository>) {
        mAdapter?.addRepositories(repositories)
        mAdapter?.notifyDataSetChanged()
        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories_list)
        setUpRecyclerView()
        mPresenter.loadRepositories()

        toolbar.title = getString(R.string.title_activity_repositories)
        showProgress()
        fab.setOnClickListener {
            showProgress()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = RepositoriesAdapter(ArrayList<Repository>(), {
            startActivity(RepositoryDetailActivity.newIntent(this, it.name))
        })
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewRepositories.adapter = mAdapter
    }

    private fun showProgress() {
        recyclerViewRepositories.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
        fab.isEnabled = false
        mPresenter.loadRepositories()
    }

    private fun hideProgress() {
        recyclerViewRepositories.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
        fab.isEnabled = true
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
        fab.isEnabled = true
    }
}

