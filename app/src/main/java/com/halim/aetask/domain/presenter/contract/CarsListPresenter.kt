package com.halim.aetask.domain.presenter.contract


interface CarsListPresenter : Presenter {

    enum class SortOption {
        END_DATE_ASC, END_DATE_DES,
        YEAR_ASC, YEAR_DES,
        PRICE_ASC, PRICE_DES
    }

    fun refreshCarsList()

    fun sortCarsList(option: SortOption)
}