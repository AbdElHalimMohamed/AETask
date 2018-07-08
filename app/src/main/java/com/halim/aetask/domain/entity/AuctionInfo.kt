package com.halim.aetask.domain.entity


data class AuctionInfo(val lotNum: Long,
                       val bids: Int,
                       val durationSec: Long,
                       val currentPrice: Long,
                       val currencyEng: String,
                       val currencyAr: String)