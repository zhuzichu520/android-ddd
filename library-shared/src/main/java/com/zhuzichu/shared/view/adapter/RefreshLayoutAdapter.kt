package com.zhuzichu.shared.view.adapter

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhuzichu.shared.command.BindingCommand

@BindingAdapter(value = ["onRefreshCommand", "onLoadMoreCommand"], requireAll = false)
fun onBindSmartRefresh(
    refreshLayout: SmartRefreshLayout,
    onRefreshCommand: BindingCommand<RefreshLayout>?,
    onLoadMoreCommand: BindingCommand<RefreshLayout>?
) {
    refreshLayout.setOnRefreshListener {
        onRefreshCommand?.execute(it)
    }
    refreshLayout.setOnLoadMoreListener {
        onLoadMoreCommand?.execute(it)
    }
}
