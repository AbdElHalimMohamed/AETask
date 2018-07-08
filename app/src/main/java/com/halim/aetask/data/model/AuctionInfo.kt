package com.halim.aetask.data.model

import com.google.gson.annotations.SerializedName


data class AuctionInfo(@SerializedName("lot") val lotNum: Long?,
                       @SerializedName("bids") val bids: Int?,
                       @SerializedName("endDate") val durationSec: Long?,
                       @SerializedName("currentPrice") val currentPrice: Long?,
                       @SerializedName("minIncrement") val minIncrement: Int?,
                       @SerializedName("currencyEn") val currencyEng: String?,
                       @SerializedName("currencyAr") val currencyAr: String?)