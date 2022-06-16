package com.zhuzichu.wan.ext

import com.zhuzichu.wan.databinding.LayoutAppBarBinding

fun LayoutAppBarBinding.initAppBar(
    title: String = "标题",
) {
    this.appbarTitle.text = title
}