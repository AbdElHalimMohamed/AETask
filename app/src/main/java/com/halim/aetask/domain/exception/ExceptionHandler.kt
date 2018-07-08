package com.halim.aetask.domain.exception

import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

// Map exceptions to domain exceptions that can be used by View
object ExceptionHandler {

    fun getException(t: Throwable): DomainException =
            when (t) {
                is HttpException -> {
                    when(t.code()) {
                        404 -> DomainException.ResourceNotFoundException()
                        500 -> DomainException.RemoteServerException()
                        else -> DomainException.UnknownException()
                    }
                }
                is SocketTimeoutException,
                is TimeoutException,
                is UnknownHostException -> DomainException.NetworkException()
                is JSONException -> DomainException.NetworkException()
                else -> DomainException.UnknownException()
            }
}