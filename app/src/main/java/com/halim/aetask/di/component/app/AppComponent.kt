package com.halim.aetask.di.component.app

import com.halim.aetask.App
import com.halim.aetask.di.module.app.ActivitiesModule
import com.halim.aetask.di.module.app.AppModule
import com.halim.aetask.di.module.app.RepositoryModule
import com.halim.aetask.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector


@AppScope
@Component(modules = [(AppModule::class), (ActivitiesModule::class), (RepositoryModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}