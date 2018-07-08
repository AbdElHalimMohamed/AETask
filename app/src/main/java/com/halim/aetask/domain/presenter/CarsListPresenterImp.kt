package com.halim.aetask.domain.presenter

import com.halim.aetask.domain.entity.Car
import com.halim.aetask.domain.entity.CarsAuction
import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import com.halim.aetask.domain.presenter.contract.CarsListPresenter
import com.halim.aetask.domain.usecase.car.CarsUseCase
import com.halim.aetask.domain.usecase.car.GetAllCarsAuctionUseCase
import com.halim.aetask.domain.usecase.observer.SimpleDisposableObserver
import com.halim.aetask.domain.view.CarsListView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class CarsListPresenterImp(view: CarsListView,
                           private val getCarsUseCase: GetAllCarsAuctionUseCase,
                           private val threadTransformer: ThreadTransformer)
    : Presenter<CarsListView>(view, getCarsUseCase), CarsListPresenter {

    private var refreshInterval = 0L
    private var autoRefreshDisposable: Disposable? = null

    override fun bindView() {
        getCars(true)
    }

    override fun refreshCarsList() {
        getCars(false)
    }

    private fun getCars(showLoader: Boolean) {
        getCarsUseCase.execute(CarsUseCase.Params.GetAllCarsParam(refreshInterval),
                object : SimpleDisposableObserver<CarsAuction>(view) {
                    override fun onStart() {
                        if (showLoader) {
                            super.onStart()
                        }
                    }

                    override fun onSuccess(data: CarsAuction) {
                        super.onSuccess(data)
                        refreshInterval = data.refreshIntervalSec

                        setCarImageWidthAndHeight(data.cars)
                        view?.showCarsList(data.cars)

                        autoRefreshCars()
                    }
                })
    }

    private fun setCarImageWidthAndHeight(cars: List<Car>) {
        cars.forEach {
            it.image = it.image.replace("w_[w],h_[h]",
                    "w_0,h_0")
        }
    }

    private fun autoRefreshCars() {
        if (autoRefreshDisposable == null) {
            autoRefreshDisposable = threadTransformer.apply(Observable.interval(refreshInterval,
                    TimeUnit.SECONDS)).subscribe {
                getCars(false)
            }
        }
    }

    override fun dispose() {
        super.dispose()

        autoRefreshDisposable?.dispose()
    }
}