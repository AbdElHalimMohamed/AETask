package com.halim.aetask.domain.entity


data class CarsAuction(val refreshIntervalSec: Long,
                       val cars: List<Car>)