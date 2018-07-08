package com.halim.aetask.ui.activity

import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.afollestad.materialdialogs.MaterialDialog
import com.halim.aetask.R
import com.halim.aetask.domain.entity.Car
import com.halim.aetask.domain.presenter.contract.CarsListPresenter
import com.halim.aetask.domain.view.CarsListView
import com.halim.aetask.ui.adapter.CarsListAdapter
import com.halim.aetask.ui.adapter.decorator.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_cars_list.*
import kotlinx.android.synthetic.main.widget_filter_bar.*


class CarsListActivity : BaseActivity<CarsListPresenter>(), CarsListView {

    private var carsAdapter: CarsListAdapter? = null
    private val sortOptions = arrayListOf(SortOption(CarsListPresenter.SortOption.YEAR_ASC, R.string.year_asc),
            SortOption(CarsListPresenter.SortOption.YEAR_DES, R.string.year_des),
            SortOption(CarsListPresenter.SortOption.END_DATE_ASC, R.string.end_date_asc),
            SortOption(CarsListPresenter.SortOption.END_DATE_DES, R.string.end_date_des),
            SortOption(CarsListPresenter.SortOption.PRICE_ASC, R.string.price_asc),
            SortOption(CarsListPresenter.SortOption.PRICE_DES, R.string.price_des))

    inner class SortOption(val option: CarsListPresenter.SortOption,
                           @StringRes private val titleRes: Int) {
        override fun toString(): String =
                resources.getString(titleRes)
    }

    override fun onViewCreated(presenter: CarsListPresenter?) {
        swipeRefresh.setOnRefreshListener {
            presenter?.refreshCarsList()
        }

        carsAdapter = CarsListAdapter()
        carsRV.adapter = carsAdapter
        carsRV.layoutManager = LinearLayoutManager(this)
        carsRV.addItemDecoration(SpacesItemDecoration(16, this))

        sortBtn.setOnClickListener {
            showSortDialog()
        }
    }

    private fun showSortDialog() {
        MaterialDialog.Builder(this)
                .items(sortOptions)
                .title(R.string.sort_by)
                .itemsCallbackSingleChoice(-1, { _, _, index, _ ->
                    presenter.sortCarsList(sortOptions[index].option)
                    true
                })
                .show()

    }

    override fun getLayoutId(): Int =
            R.layout.activity_cars_list

    override fun getToolbar(): Toolbar? =
            listToolbar

    override fun showHomeAsUp(): Boolean =
            true

    override fun getActivityTitle(): String? =
            getString(R.string.car_list_title)

    override fun hideLoading() {
        super<BaseActivity>.hideLoading()

        swipeRefresh.isRefreshing = false
    }

    override fun showCarsList(cars: List<Car>) {
        carsAdapter?.setItems(cars)
    }

    override fun updateCarsList(cars: List<Car>) {
        carsAdapter?.updateItems(cars)
    }
}