package com.zhuzichu.wan.home

import android.app.Application
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.load
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.vo.ArticleVo
import com.zhuzichu.application.vo.BannerVo
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand
import com.zhuzichu.shared.tool.toCast
import com.zhuzichu.shared.view.adapter.ViewState
import com.zhuzichu.wan.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject


private const val TAG = "HomeMainViewModel"

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    application: Application,
    private val articleApplicationService: ArticleApplicationService
) : BaseViewModel(application) {

    private var page = 0

    val items = MutableLiveData<List<Any>>(listOf())

    val viewState = MutableLiveData(ViewState.LOADING)

    val itemBinding = OnItemBindClass<Any>().apply {
        map(ArticleVo::class.java, BR.item, R.layout.item_article)
        map(BannerGroupVo::class.java, BR.item, R.layout.item_article_banner)
    }

    val onRefreshCommand = BindingCommand<RefreshLayout>(consumer = {
        page = 0
        onLoadListCommand.execute(it)
    })

    val onLoadListCommand = BindingCommand<RefreshLayout>(consumer = {
        viewModelScope.launch {
            try {
                val pageVo = articleApplicationService.getArticleList(page)
                val data = pageVo.datas ?: listOf()
                if (page == 0) {
                    items.value = listOf(
                        BannerGroupVo(
                            articleApplicationService.getBannerList(),
                            this@HomeMainViewModel
                        )
                    )
                }
                items.value = items.value!! + data
                viewState.value = ViewState.SUCCESS
                if (0 == pageVo.total) {
                    viewState.value = ViewState.EMPTY
                }
            } catch (e: Exception) {
                if (page == 0) {
                    viewState.value = ViewState.ERROR
                }
                handleException(e)
            } finally {
                page += 1
                it?.finishLoadMore()
                it?.finishRefresh()
            }
        }
    })

    val onLoadTopCommand = BindingCommand<Unit>(execute = {
        viewModelScope.launch {
            try {
                val list = articleApplicationService.getTopArticle()
                toast(list.size.toString())
            } catch (e: Exception) {
                handleException(e)
            } finally {
            }
        }
    })

}

@BindingAdapter(value = ["bindBanner"], requireAll = false)
fun onBindBannerView(banner: Banner<*, *>, list: List<BannerVo>?) {
    list?.let {
        val adapter = object : BannerImageAdapter<BannerVo>(list) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: BannerVo?,
                position: Int,
                size: Int
            ) {
                holder?.imageView?.load(data?.imagePath)
            }
        }
        banner.setAdapter(adapter.toCast())
    }
}