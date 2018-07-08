package com.halim.aetask.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.halim.aetask.R
import com.halim.aetask.domain.exception.DomainException
import com.halim.aetask.domain.presenter.contract.Presenter
import com.halim.aetask.domain.view.View
import com.halim.aetask.ui.util.ErrorHandler
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.widget_empty_screen.*
import kotlinx.android.synthetic.main.widget_error_view.*
import javax.inject.Inject

abstract class BaseActivity<P : Presenter> : AppCompatActivity(), View {

    @Inject
    lateinit var presenter: P

    abstract fun getLayoutId(): Int
    abstract fun onViewCreated(presenter: P?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)

        AndroidInjection.inject(this)

        val layoutId = getLayoutId()

        if (layoutId > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                content.isTransitionGroup = true
            }
            LayoutInflater.from(this).inflate(layoutId, content, true)
        }

        val toolbar = getToolbar()

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            if (showHomeAsUp()) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
            }
        }

        onViewCreated(presenter)

        val title = getActivityTitle()
        if (title != null) {
            this.title = title
        }

        presenter.bindView()
    }

    open fun getEmptyViewMsgRes(): Int = R.string.empty_default

    open fun getActivityTitle(): String? = null

    open fun getToolbar(): Toolbar? = null

    open fun showHomeAsUp() = false

    override fun showLoading() {
        showLoader()
    }

    override fun hideLoading() {
        fullScreenLoader.visibility = android.view.View.INVISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
            when (item?.itemId) {
                android.R.id.home -> {
                    supportFinishAfterTransition()
                    true
                }
                else -> {
                    false
                }
            }

    override fun showEmptyResult() {
        showEmptyView()

        emptyMsg.text = getString(getEmptyViewMsgRes())
    }

    override fun showErrorMsg(exception: DomainException) {
        showErrorView()

        errorMsg.text = ErrorHandler.getErrorMessage(this, exception)
    }

    private fun showErrorView() {
        errorView.visibility = VISIBLE
        emptyView.visibility = INVISIBLE
        fullScreenLoader.visibility = INVISIBLE
    }

    private fun showEmptyView() {
        errorView.visibility = INVISIBLE
        emptyView.visibility = VISIBLE
        fullScreenLoader.visibility = INVISIBLE
    }

    private fun showLoader() {
        fullScreenLoader.visibility = VISIBLE
        errorView.visibility = INVISIBLE
        emptyView.visibility = INVISIBLE
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}