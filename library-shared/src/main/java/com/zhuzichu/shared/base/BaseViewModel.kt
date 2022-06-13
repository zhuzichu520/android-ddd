package com.zhuzichu.shared.base

import android.app.Application
import com.rxjava.rxlife.ScopeViewModel
import com.zhuzichu.shared.event.SingleLiveEvent

abstract class BaseViewModel(application: Application) : ScopeViewModel(application) {

    val onToastEvent = SingleLiveEvent<String>()
    val onLoadingEvent = SingleLiveEvent<Boolean>()
    val onExceptionEvent = SingleLiveEvent<Exception>()

    fun toast(text: String?) {
        onToastEvent.value = text
    }

    fun showLoading() {
        onLoadingEvent.value = true
    }

    fun hideLoading() {
        onLoadingEvent.value = false
    }

    fun handleException(e: Exception) {
        onExceptionEvent.value = e
    }

}