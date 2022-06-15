package com.zhuzichu.shared.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding4.view.clicks
import com.zhuzichu.shared.command.BindingCommand
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.state.ErrorState


@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onBindMultiStateContainer(
    container: MultiStateContainer,
    clickCommand: BindingCommand?,
    isThrottleFirst: Boolean?
) {
    container.show(ErrorState::class.java)
}
