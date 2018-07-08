package com.halim.aetask.domain.view

import com.halim.aetask.domain.exception.DomainException


interface View {

    fun showLoading() {}

    fun hideLoading() {}

    fun showEmptyResult() {}

    fun hideEmptyResult() {}

    fun showMsg(msg: String) {}

    fun showErrorMsg(exception: DomainException) {}

    fun close() {}
}