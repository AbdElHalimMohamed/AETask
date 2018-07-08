package com.halim.aetask.ui.util

import android.content.Context
import com.halim.aetask.R
import com.halim.aetask.domain.exception.DomainException


object ErrorHandler {

    fun getErrorMessage(context: Context, exception: DomainException): String? =
            context.getString(when (exception) {
                is DomainException.RemoteServerException -> R.string.server_error
                is DomainException.ResourceNotFoundException -> R.string.not_found_error
                is DomainException.NetworkException -> R.string.network_error
                is DomainException.UnknownException -> R.string.unknown_error
                else -> R.string.unknown_error
            })
}