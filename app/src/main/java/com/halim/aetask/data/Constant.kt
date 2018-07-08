package com.halim.aetask.data


object Constant {

    // default values for nullable properties returned from "Datasets"
    object Default {

        const val ID = -1L
        const val INT = 0
        const val STRING = ""
        const val LONG = 0L
    }

    object Network {
        // 10MB
        val NetWorkCacheSize = 10L * 1024 * 1024
    }
}