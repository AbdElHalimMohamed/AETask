package com.halim.aetask.data.mapper

import com.halim.aetask.data.model.CarsAuction as CarsAuctionModel
import com.halim.aetask.domain.entity.CarsAuction as CarsAuctionEntity


class CarsAuctionMapper(private val carMapper: CarMapper)
    : Mapper<CarsAuctionModel, CarsAuctionEntity>() {

    override fun transform(from: CarsAuctionModel?): CarsAuctionEntity? =
            from?.let {
                CarsAuctionEntity(it.refreshIntervalSec,
                        carMapper.transform(it.cars) ?: arrayListOf())
            }
}