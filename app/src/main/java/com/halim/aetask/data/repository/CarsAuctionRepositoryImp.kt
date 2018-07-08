package com.halim.aetask.data.repository

import com.halim.aetask.data.Constant.Default
import com.halim.aetask.data.dataset.CarsAuctionDataSet
import com.halim.aetask.data.mapper.CarsAuctionMapper
import com.halim.aetask.domain.entity.CarsAuction
import com.halim.aetask.domain.repository.CarsAuctionRepository
import io.reactivex.Single


class CarsAuctionRepositoryImp(private val dataSet: CarsAuctionDataSet,
                               private val mapper: CarsAuctionMapper)
    : CarsAuctionRepository {

    override fun getAuctionCars(refreshIntervalSec: Long): Single<CarsAuction> =
            dataSet.getCars(refreshIntervalSec).map {
                mapper.transform(it) ?: CarsAuction(Default.LONG, arrayListOf())
            }

}