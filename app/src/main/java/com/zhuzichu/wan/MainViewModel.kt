package com.zhuzichu.wan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhuzichu.application.service.UserApplicationService
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand
import com.zhuzichu.shared.tool.object2Json
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val userApplicationService: UserApplicationService,
) : BaseViewModel(application) {

    val userInfo = MutableLiveData<String>()

    val onGetUserCommand = BindingCommand<Unit>(execute = {
        showLoading()
        viewModelScope.launch {
            try {
                val user = userApplicationService.getUserByLocal()
                userInfo.value = object2Json(user) ?: ""
                toast(user.nickname)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                hideLoading()
            }
        }
    })

}