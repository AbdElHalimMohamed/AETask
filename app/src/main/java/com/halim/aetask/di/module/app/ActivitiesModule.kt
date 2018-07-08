package com.halim.aetask.di.module.app

import com.halim.aetask.di.module.activity.CarsListActivityModule
import com.halim.aetask.di.scope.ActivityScope
import com.halim.aetask.ui.activity.CarsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CarsListActivityModule::class)])
    abstract fun createCarsListActivityInjector(): CarsListActivity
}