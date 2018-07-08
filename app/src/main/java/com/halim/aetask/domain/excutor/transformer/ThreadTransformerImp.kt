package com.halim.aetask.domain.excutor.transformer

import com.halim.aetask.domain.excutor.PostExecutionThread
import com.halim.aetask.domain.excutor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ThreadTransformerImp @Inject constructor(private val threadExecutor: ThreadExecutor,
                                               private val postExecutionThread: PostExecutionThread)
    : ThreadTransformer {

    override fun <T> apply(upstream: Single<T>): Single<T> =
            upstream.compose({
                it.subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.scheduler)
            })

    override fun <T> apply(upstream: Observable<T>): Observable<T> =
            upstream.compose({
                it.subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.scheduler)
            })
}