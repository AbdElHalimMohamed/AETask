package com.halim.aetask.data.dataset

import com.halim.aetask.data.model.CarsAuction
import io.reactivex.Single

// a common interface for all source of data (CloudDataSet, DatabaseDataSet, FileDataSet...etc)
interface CarsAuctionDataSet {

    fun getCars(refreshIntervalSec: Long): Single<CarsAuction>
}