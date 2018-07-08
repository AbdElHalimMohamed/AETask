package com.halim.aetask.data.model

import com.google.gson.annotations.SerializedName


data class Car(@SerializedName("carID") val id: Long?,
               @SerializedName("makeEn") val makeEng: String?,
               @SerializedName("modelEn") val modelEng: String?,
               @SerializedName("makeAr") val makeAr: String?,
               @SerializedName("modelAr") val modelAr: String?,
               @SerializedName("year") val year: Int?,
               @SerializedName("image") val image: String?,
               @SerializedName("AuctionInfo") val auctionInfo: AuctionInfo?)