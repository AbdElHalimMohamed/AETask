package com.halim.aetask.di.module.activity

import com.halim.aetask.di.scope.ActivityScope
import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import com.halim.aetask.domain.presenter.CarsListPresenterImp
import com.halim.aetask.domain.presenter.contract.CarsListPresenter
import com.halim.aetask.domain.repository.CarsAuctionRepository
import com.halim.aetask.domain.usecase.car.GetAllCarsAuctionUseCase
import com.halim.aetask.domain.view.CarsListView
import com.halim.aetask.ui.activity.CarsListActivity
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [(CarsListActivityModule.Bind::class)])
class CarsListActivityModule {

    @Provides
    @ActivityScope
    fun provideGetAllCarsAuctionUseCase(repo: CarsAuctionRepository,
                                        threadTransformer: ThreadTransformer) =
            GetAllCarsAuctionUseCase(repo, threadTransformer)


    @Provides
    @ActivityScope
    fun providePresenter(view: CarsListView,
                         getCarsUseCase: GetAllCarsAuctionUseCase,
                         threadTransformer: ThreadTransformer): CarsListPresenter =
            CarsListPresenterImp(view, getCarsUseCase, threadTransformer)

    @Module
    abstract class Bind {
        @Binds
        @ActivityScope
        abstract fun bindView(activity: CarsListActivity): CarsListView
    }
}