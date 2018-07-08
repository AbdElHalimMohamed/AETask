package com.halim.aetask.di.module.app


import com.halim.aetask.data.dataset.CarsAuctionDataSet
import com.halim.aetask.data.dataset.cloud.CarsAuctionDataSetCloud
import com.halim.aetask.data.dataset.cloud.rest.AuctionAPI
import com.halim.aetask.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [(NetworkModule::class)])
class DataSetModule {

    @Provides
    @AppScope
    fun provideAuctionAPI(retrofit: Retrofit): AuctionAPI =
            retrofit.create(AuctionAPI::class.java)

    @Provides
    @AppScope
    fun provideCarsAuctionDataSet(auctionAPI: AuctionAPI): CarsAuctionDataSet =
            CarsAuctionDataSetCloud(auctionAPI)
}