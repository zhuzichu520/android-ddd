package com.zhuzichu.shared.base

import android.app.Application
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavOptions
import com.rxjava.rxlife.ScopeViewModel
import com.zhuzichu.shared.base.payload.NavigatePayload
import com.zhuzichu.shared.event.SingleLiveEvent

abstract class BaseViewModel(application: Application) : ScopeViewModel(application) {

    internal val onToastEvent = SingleLiveEvent<String>()
    internal val onLoadingEvent = SingleLiveEvent<Boolean>()
    internal val onExceptionEvent = SingleLiveEvent<Exception>()
    internal val onNavigateEvent = SingleLiveEvent<NavigatePayload>()

    fun toast(text: String?) {
        onToastEvent.value = text ?: ""
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

    fun navigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
        onNavigateEvent.value = NavigatePayload(resId, args, navOptions)
    }

}