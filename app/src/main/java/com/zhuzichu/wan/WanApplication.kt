package com.zhuzichu.wan

import android.app.Application
import com.zhuzichu.application.init.ApplicationInit
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationInit(this)
    }

}