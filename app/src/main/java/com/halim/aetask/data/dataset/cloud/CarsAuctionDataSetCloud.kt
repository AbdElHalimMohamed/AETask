package com.halim.aetask.data.dataset.cloud

import com.halim.aetask.data.Constant
import com.halim.aetask.data.dataset.CarsAuctionDataSet
import com.halim.aetask.data.dataset.cloud.rest.AuctionAPI
import com.halim.aetask.data.model.CarsAuction
import io.reactivex.Single


class CarsAuctionDataSetCloud(private val auctionAPI: AuctionAPI) : CarsAuctionDataSet {

    override fun getCars(refreshIntervalSec: Long): Single<CarsAuction> =
            auctionAPI.getCars(refreshIntervalSec)
                    .map {
                        CarsAuction(it.refreshIntervalSec ?: Constant.Default.LONG,
                                it.cars ?: arrayListOf())
                    }

}