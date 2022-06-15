package com.zhuzichu.wan.profile

import android.app.Application
import android.util.Log
import com.zhuzichu.application.service.UserApplicationService
import com.zhuzichu.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "ProfileMainViewModel"

@HiltViewModel
class ProfileMainViewModel @Inject constructor(
    application: Application,
    private val userApplicationService: UserApplicationService,
) : BaseViewModel(application) {

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }


}