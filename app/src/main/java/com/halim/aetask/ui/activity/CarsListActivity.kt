package com.halim.aetask.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.halim.aetask.R
import com.halim.aetask.domain.entity.Car
import com.halim.aetask.domain.presenter.contract.CarsListPresenter
import com.halim.aetask.domain.view.CarsListView
import com.halim.aetask.ui.adapter.CarsListAdapter
import com.halim.aetask.ui.adapter.decorator.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_cars_list.*


class CarsListActivity : BaseActivity<CarsListPresenter>(), CarsListView {

    private var carsAdapter: CarsListAdapter? = null

    override fun onViewCreated(presenter: CarsListPresenter?) {
        swipeRefresh.setOnRefreshListener {
            presenter?.refreshCarsList()
        }

        carsAdapter = CarsListAdapter()
        carsRV.adapter = carsAdapter
        carsRV.layoutManager = LinearLayoutManager(this)
        carsRV.addItemDecoration(SpacesItemDecoration(16, this))
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
        if (carsAdapter?.itemCount ?: 0 > 0) {
            carsAdapter?.updateItems(cars)
        } else {
            carsAdapter?.setItems(cars)
        }
    }
}