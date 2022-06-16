package com.zhuzichu.wan.home

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.load
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.vo.ArticleVo
import com.zhuzichu.application.vo.BannerVo
import com.zhuzichu.shared.base.BaseViewModel
import com.zhuzichu.shared.command.BindingCommand
import com.zhuzichu.shared.tool.toCast
import com.zhuzichu.shared.view.BindingViewHolder
import com.zhuzichu.shared.view.adapter.ViewState
import com.zhuzichu.wan.R
import com.zhuzichu.wan.databinding.ItemBannerBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import me.tatarka.bindingcollectionadapter2.toItemBinding
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    application: Application,
    private val articleApplicationService: ArticleApplicationService
) : BaseViewModel(application), ArticleListeners {

    private var page = 0

    val items = ObservableArrayList<Any>()

    val viewState = MutableLiveData(ViewState.LOADING)

    val itemBinding = OnItemBindClass<Any>().apply {
        map(ArticleVo::class.java, BR.item, R.layout.item_article)
        map(BannerGroupVo::class.java, BR.item, R.layout.item_article_banner)
    }.toItemBinding().bindExtra(BR.listeners, this)

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
                    items.clear()
                    items.add(
                        BannerGroupVo(
                            articleApplicationService.getBannerList(),
                            this@HomeMainViewModel
                        )
                    )
                    items.addAll(
                        articleApplicationService.getTopArticle()
                    )
                }
                items.addAll(data)
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

    override fun onClickArticleListener(url: String) {
        toast(url)
    }

}


@BindingAdapter(value = ["bindHomeBanner"], requireAll = false)
fun onBindHomeBanner(banner: Banner<*, *>, list: List<BannerVo>?) {
    list?.let {
        val adapter = object : BannerAdapter<BannerVo, BindingViewHolder>(list) {
            override fun onBindView(
                holder: BindingViewHolder?,
                data: BannerVo?,
                position: Int,
                size: Int
            ) {
                holder ?: return
                val binding: ItemBannerBinding =
                    DataBindingUtil.getBinding(holder.itemView) ?: return
                binding.image.load(data?.imagePath)
                binding.title.text = data?.title
                binding.root.setOnClickListener {
                    Toast.makeText(banner.context, data?.url, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BindingViewHolder {
                val binding: ItemBannerBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(banner.context),
                    R.layout.item_banner,
                    parent,
                    false
                )
                return BindingViewHolder(binding.root)
            }
        }
        banner.setAdapter(adapter.toCast())
    }
}