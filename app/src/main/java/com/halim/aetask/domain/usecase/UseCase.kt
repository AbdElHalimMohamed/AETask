package com.halim.aetask.domain.usecase

import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


abstract class UseCase<T, in Params>(private val threadTransformer: ThreadTransformer) {

    private var disposables: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Single<T>

    fun execute(params: Params, observer: DisposableSingleObserver<T>) {

        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        val single = buildUseCaseObservable(params)

        disposables.add(threadTransformer.apply(single)
                .subscribeWith(observer))
    }

    fun dispose() {
        if (disposables.isDisposed.not())
            disposables.dispose()
    }
}