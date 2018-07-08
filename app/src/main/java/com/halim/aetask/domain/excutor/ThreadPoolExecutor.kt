package com.halim.aetask.domain.excutor

import java.util.concurrent.Executors
import javax.inject.Inject


class ThreadPoolExecutor @Inject constructor(): ThreadExecutor {

    private val executor = Executors.newCachedThreadPool()

    override fun execute(r: Runnable?) {
        executor.execute(r)
    }
}