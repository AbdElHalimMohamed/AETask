package com.halim.aetask.domain.excutor.transformer

import io.reactivex.Observable
import io.reactivex.Single


interface ThreadTransformer {

    fun <T> apply(upstream: Single<T>): Single<T>

    fun <T> apply(upstream: Observable<T>): Observable<T>
}