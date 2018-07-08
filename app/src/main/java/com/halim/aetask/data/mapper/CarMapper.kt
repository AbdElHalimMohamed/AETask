package com.halim.aetask.data.mapper

import com.halim.aetask.data.Constant.Default
import com.halim.aetask.data.model.Car as CarModel
import com.halim.aetask.domain.entity.Car as CarEntity


class CarMapper(private val auctionInfoMapper: AuctionInfoMapper)
    : Mapper<CarModel, CarEntity>() {

    override fun transform(from: CarModel?): CarEntity? =
            from?.let {
                CarEntity(it.id ?: Default.ID,
                        it.makeEng ?: Default.STRING,
                        it.modelEng ?: Default.STRING,
                        it.makeAr ?: Default.STRING,
                        it.modelAr ?: Default.STRING,
                        it.year ?: Default.INT,
                        it.image ?: Default.STRING,
                        auctionInfoMapper.transform(it.auctionInfo))
            }
}