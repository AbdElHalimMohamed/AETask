package com.halim.aetask.domain.excutor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
