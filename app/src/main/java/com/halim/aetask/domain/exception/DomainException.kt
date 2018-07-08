package com.halim.aetask.domain.exception


sealed class DomainException {

    class RemoteServerException : DomainException()

    class ResourceNotFoundException : DomainException()

    class AuthinticationException : DomainException()

    class NetworkException : DomainException()

    class UnknownException : DomainException()
}