package com.jamesoverclock.kotlinmvp.flow.repository

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.jamesoverclock.kotlinmvp.R
import com.jamesoverclock.kotlinmvp.formatDate
import com.jamesoverclock.kotlinmvp.model.RepositoryDetail
import com.jamesoverclock.kotlinmvp.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_repository_detail.*

class RepositoryDetailActivity : BaseMvpActivity<RepositoryDetailsContract.View,
        RepositoryDetailsContract.Presenter>(),
        RepositoryDetailsContract.View {

    override var mPresenter: RepositoryDetailsContract.Presenter = RepositoryDetailsPresenter()

    companion object {
        private const val NAME = "name"

        fun newIntent(context: Context, name: String): Intent =
                Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra(NAME, name)
                }
    }

    override fun showRepository(repositoryDetail: RepositoryDetail) {
        layoutContent.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        with(repositoryDetail) {
            repository_name.text = name
            textCreatedDate.text = created_at.formatDate()
            textUpdatedDate.text = updated_at.formatDate()
            textDescription.text = description
            textForks.text = forks_count
            textStars.text = stargazers_count
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_detail)

        layoutContent.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        mPresenter.loadRepository(intent.getStringExtra(NAME))

        btnReload.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
            mPresenter.loadRepository(intent.getStringExtra(NAME))
        }
        toolbar.title = getString(R.string.title_activity_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            it.setOnClickListener({
                finish()
            })
        }
    }

    override fun showReloadButton() {
        progressBar.visibility = View.GONE
        btnReload.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
