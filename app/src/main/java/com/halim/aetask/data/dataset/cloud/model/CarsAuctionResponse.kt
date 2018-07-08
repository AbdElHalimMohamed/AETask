package com.halim.aetask.data.dataset.cloud.model

import com.google.gson.annotations.SerializedName
import com.halim.aetask.data.model.Car


data class CarsAuctionResponse(@SerializedName("RefreshInterval") val refreshIntervalSec: Long?,
                               @SerializedName("Ticks") val ticks: Long?,
                               @SerializedName("Cars") val cars: List<Car>?)