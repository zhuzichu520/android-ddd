package com.zhuzichu.wan.navi

import android.app.Application
import com.zhuzichu.application.service.UserApplicationService
import com.zhuzichu.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NaviMainViewModel @Inject constructor(
    application: Application,
    private val userApplicationService: UserApplicationService,
) : BaseViewModel(application) {


}