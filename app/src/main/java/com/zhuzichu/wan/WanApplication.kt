package com.zhuzichu.wan

import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhuzichu.application.init.ApplicationInit
import com.zy.multistatepage.MultiStateConfig
import com.zy.multistatepage.MultiStatePage
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = MultiStateConfig.Builder()
            .alphaDuration(300)
            .emptyMsg("空空如也")
            .loadingMsg("加载中...")
            .errorMsg("页面错误")
            .build()
        MultiStatePage.config(config)
        ApplicationInit(this)
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(
                context
            )
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(
                context
            ).setDrawableSize(20f)
        }
    }

}