package com.halim.aetask.domain.presenter

import com.halim.aetask.domain.presenter.contract.Presenter
import com.halim.aetask.domain.usecase.UseCase
import com.halim.aetask.domain.view.View
import java.lang.ref.WeakReference


abstract class Presenter<out V : View>(view: V,
                                       private vararg val useCases: UseCase<*, *>)
    : Presenter {

    private val viewReference: WeakReference<V> = WeakReference(view)
    val view: V?
        get() = viewReference.get()

    override fun dispose() {

        useCases.forEach {
            it.dispose()
        }
    }
}