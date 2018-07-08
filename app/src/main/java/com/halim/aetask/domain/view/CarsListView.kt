package com.halim.aetask.domain.view

import com.halim.aetask.domain.entity.Car


interface CarsListView : View {

    fun showCarsList(cars: List<Car>)
}