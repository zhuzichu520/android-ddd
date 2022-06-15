package com.zhuzichu.wan.home

import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.R
import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.wan.databinding.FragmentMainHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainFragment : BaseFragment<FragmentMainHomeBinding, HomeMainViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun initView() {
        super.initView()

    }

}