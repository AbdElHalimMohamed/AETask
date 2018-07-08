package com.halim.aetask.data.mapper

import com.halim.aetask.data.Constant.Default
import com.halim.aetask.data.model.AuctionInfo as AuctionInfoModel
import com.halim.aetask.domain.entity.AuctionInfo as AuctionInfoEntity


class AuctionInfoMapper : Mapper<AuctionInfoModel, AuctionInfoEntity>() {

    override fun transform(from: AuctionInfoModel?): AuctionInfoEntity? =
            from?.let {
                AuctionInfoEntity(it.lotNum ?: Default.LONG,
                        it.bids ?: Default.INT,
                        it.durationSec ?: Default.LONG,
                        it.currentPrice ?: Default.LONG,
                        it.currencyEng ?: Default.STRING,
                        it.currencyAr ?: Default.STRING)
            }
}