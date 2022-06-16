package com.zhuzichu.shared.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding4.view.clicks
import com.zhuzichu.shared.command.BindingCommand
import com.zhuzichu.shared.tool.hideView
import com.zhuzichu.shared.tool.showView
import com.zhuzichu.shared.tool.toCast
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

private fun <T : Any> Observable<T>.isThrottleFirst(
    isThrottleFirst: Boolean
): Observable<T> {
    return this.compose {
        if (isThrottleFirst) {
            it.throttleFirst(300L, TimeUnit.MILLISECONDS)
        } else {
            it
        }
    }
}

@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onBindClickCommand(view: View, clickCommand: BindingCommand<*>?, isThrottleFirst: Boolean?) {
    clickCommand?.apply {
        view.clicks().isThrottleFirst(isThrottleFirst ?: true).subscribe {
            execute()
        }
    }
}

@BindingAdapter(value = ["isDisplay"], requireAll = false)
fun onBindViewVisibility(view: View, isDisplay: Boolean?) {
    isDisplay?.let {
        if (it) {
            showView(view)
        } else {
            hideView(view)
        }
    }
}

@BindingAdapter(value = ["initView"], requireAll = false)
fun onBindInitView(view: View, initViewCommand: BindingCommand<*>?) {
    initViewCommand?.execute(view.toCast())
}