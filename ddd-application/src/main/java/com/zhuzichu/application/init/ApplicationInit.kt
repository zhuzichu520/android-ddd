package com.zhuzichu.application.init

import android.app.Application
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import java.util.concurrent.TimeUnit

class ApplicationInit(application: Application) {

    init {
        MMKV.initialize(application)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
        RxHttpPlugins.init(okHttpClient)
            .setDebug(true)
            .setOnParamAssembly {
                it
            }
    }

}