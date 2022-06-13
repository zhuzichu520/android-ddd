package com.zhuzichu.wan

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.service.UserService
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val userService: UserService,
) : BaseViewModel(application) {

    val username = MutableLiveData<String>("zhuzichu520@gmail.com")

    val password = MutableLiveData<String>("qaioasd520")

    val onLoginCommand = BindingCommand(execute = {
        showLoading()
        viewModelScope.launch {
            try {
                val userVo = userService.login(LoginParam(username.value, password.value))
                toast(userVo.nickname)
            } catch (e: Exception) {
                handleException(e)
            } finally {
                hideLoading()
            }
        }
    })

}