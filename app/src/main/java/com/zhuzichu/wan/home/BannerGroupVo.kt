package com.zhuzichu.wan.home

import com.youth.banner.Banner
import com.zhuzichu.application.vo.BannerVo
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand

class BannerGroupVo(
    var list: List<BannerVo>,
    viewModel: BaseViewModel
) {

    val onInitCommand = BindingCommand<Banner<*, *>>(
        consumer = {
            it?.addBannerLifecycleObserver(viewModel.viewLifecycleOwner)
        }
    )

}