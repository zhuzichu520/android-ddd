package com.zhuzichu.shared.base

import android.app.Application
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.rxjava.rxlife.ScopeViewModel
import com.zhuzichu.shared.base.payload.NavigatePayload
import com.zhuzichu.shared.event.SingleLiveEvent

abstract class BaseViewModel(application: Application) : ScopeViewModel(application),
    LifecycleEventObserver {

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

    fun navigate(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        onNavigateEvent.value = NavigatePayload(resId, args, navOptions, navigatorExtras)
    }

    open fun onCreate() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroy() {}

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                onCreate()
            }
            Lifecycle.Event.ON_START -> {
                onStart()
            }
            Lifecycle.Event.ON_RESUME -> {
                onResume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                onPause()
            }
            Lifecycle.Event.ON_STOP -> {
                onStop()
            }
            Lifecycle.Event.ON_DESTROY -> {
                onDestroy()
            }
            else -> {}
        }
    }

}