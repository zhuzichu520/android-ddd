package com.zhuzichu.wan.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.service.UserApplicationService
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand
import com.zhuzichu.wan.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeMainViewModel"

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    application: Application,
    private val articleApplicationService: ArticleApplicationService
) : BaseViewModel(application) {

    val onLoadListCommand = BindingCommand(execute = {
        showLoading()
        viewModelScope.launch {
            try {
                val list = articleApplicationService.getArticleList()
                toast(list.datas?.size?.toString())
            } catch (e: Exception) {
                handleException(e)
            } finally {
                hideLoading()
            }
        }
    })

    val onLoadTopCommand = BindingCommand(execute = {
        showLoading()
        viewModelScope.launch {
            try {
                val list = articleApplicationService.getTopArticle()
                toast(list.size.toString())
            } catch (e: Exception) {
                handleException(e)
            } finally {
                hideLoading()
            }
        }
    })


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