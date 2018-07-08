package com.halim.aetask.di.module.app

import com.halim.aetask.data.dataset.CarsAuctionDataSet
import com.halim.aetask.data.mapper.AuctionInfoMapper
import com.halim.aetask.data.mapper.CarMapper
import com.halim.aetask.data.mapper.CarsAuctionMapper
import com.halim.aetask.data.repository.CarsAuctionRepositoryImp
import com.halim.aetask.di.scope.AppScope
import com.halim.aetask.domain.repository.CarsAuctionRepository
import dagger.Module
import dagger.Provides


@Module(includes = [(DataSetModule::class)])
class RepositoryModule {

    //======================= Cars Repository =======================
    @Provides
    @AppScope
    fun provideCarsRepository(dataSet: CarsAuctionDataSet,
                              mapper: CarsAuctionMapper): CarsAuctionRepository =
            CarsAuctionRepositoryImp(dataSet, mapper)


    @Provides
    @AppScope
    fun provideCarsAuctionMapper(carMapper: CarMapper) =
            CarsAuctionMapper(carMapper)

    @Provides
    @AppScope
    fun provideCarMapper(auctionInfoMapper: AuctionInfoMapper) =
            CarMapper(auctionInfoMapper)

    @Provides
    @AppScope
    fun provideAuctionInfoMapper() =
            AuctionInfoMapper()
}