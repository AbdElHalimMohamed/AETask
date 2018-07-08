package com.halim.aetask.domain.usecase.observer

import com.halim.aetask.domain.exception.ExceptionHandler
import com.halim.aetask.domain.view.View
import io.reactivex.observers.DisposableSingleObserver


open class SimpleDisposableObserver<T>(private val view: View?) :
        DisposableSingleObserver<T>() {

    override fun onStart() {
        super.onStart()
        view?.showLoading()
    }

    override fun onSuccess(data: T) {
        view?.hideLoading()
    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        view?.showErrorMsg(exception)
    }
}