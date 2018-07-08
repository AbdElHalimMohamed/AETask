package com.halim.aetask.domain.repository

import com.halim.aetask.domain.entity.CarsAuction
import io.reactivex.Single


interface CarsAuctionRepository {

    fun getAuctionCars(refreshIntervalSec: Long): Single<CarsAuction>
}