package com.halim.aetask.di.module.app

import android.app.Application
import android.content.Context
import com.halim.aetask.App
import com.halim.aetask.di.qualifier.AppQualifier
import com.halim.aetask.di.scope.AppScope
import com.halim.aetask.domain.excutor.PostExecutionThread
import com.halim.aetask.domain.excutor.ThreadExecutor
import com.halim.aetask.domain.excutor.ThreadPoolExecutor
import com.halim.aetask.domain.excutor.UIExecutor
import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import com.halim.aetask.domain.excutor.transformer.ThreadTransformerImp
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [(AndroidInjectionModule::class), (AppModule.Bind::class)])
class AppModule {


    @Module
    abstract class Bind {

        @Binds
        @AppScope
        abstract fun bindApp(app: App): Application

        @Binds
        @AppScope
        @AppQualifier
        abstract fun bindAppContext(context: App): Context

        @Binds
        @AppScope
        abstract fun bindThreadExecutor(threadPoolExecutor: ThreadPoolExecutor): ThreadExecutor

        @Binds
        @AppScope
        abstract fun bindUIExecutor(uiExecutor: UIExecutor): PostExecutionThread

        @Binds
        @AppScope
        abstract fun bindThreadTransformer(threadTransformer: ThreadTransformerImp): ThreadTransformer
    }
}