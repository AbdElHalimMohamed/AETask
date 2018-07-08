package com.halim.aetask.data.dataset.cloud.rest

import com.halim.aetask.data.dataset.cloud.model.CarsAuctionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface AuctionAPI {

    @GET("/v2/carsonline")
    fun getCars(@Query("RefreshInterval") refreshIntervalSec: Long):
            Single<CarsAuctionResponse>
}