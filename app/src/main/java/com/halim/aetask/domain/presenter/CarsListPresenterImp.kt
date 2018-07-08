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
import java.util.concurrent.TimeUnit


class CarsListPresenterImp(view: CarsListView,
                           private val getCarsUseCase: GetAllCarsAuctionUseCase,
                           private val threadTransformer: ThreadTransformer)
    : Presenter<CarsListView>(view, getCarsUseCase), CarsListPresenter {

    private var refreshInterval = 0L
    private var autoRefreshDisposable: Disposable? = null
    private var cars: List<Car> = arrayListOf()
    private var sortOption = CarsListPresenter.SortOption.END_DATE_ASC

    override fun bindView() {
        getCars(true)
    }

    override fun refreshCarsList() {
        cars = arrayListOf()
        getCars(false)
    }

    override fun sortCarsList(option: CarsListPresenter.SortOption) {
        if (sortOption != option) {
            sortCars(option, true)
        }
    }

    private fun sortCars(option: CarsListPresenter.SortOption, resetView: Boolean) {

        val sortedList = when (option) {
            CarsListPresenter.SortOption.PRICE_ASC -> cars.sortedBy { it.auctionInfo?.currentPrice }
            CarsListPresenter.SortOption.PRICE_DES -> cars.sortedByDescending { it.auctionInfo?.currentPrice }
            CarsListPresenter.SortOption.END_DATE_ASC -> cars.sortedBy { it.auctionInfo?.durationSec }
            CarsListPresenter.SortOption.END_DATE_DES -> cars.sortedByDescending { it.auctionInfo?.durationSec }
            CarsListPresenter.SortOption.YEAR_ASC -> cars.sortedBy { it.year }
            CarsListPresenter.SortOption.YEAR_DES -> cars.sortedByDescending { it.year }
        }

        if (resetView) {
            view?.showCarsList(sortedList)
        } else {
            view?.updateCarsList(sortedList)
        }

        sortOption = option
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

                        val resetView = cars.size != data.cars.size

                        refreshInterval = data.refreshIntervalSec
                        cars = data.cars

                        setCarImageWidthAndHeight(data.cars)
                        sortCars(sortOption, resetView)

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