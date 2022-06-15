package com.zhuzichu.wan.ques

import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.R
import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.wan.databinding.FragmentMainQuesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuesMainFragment : BaseFragment<FragmentMainQuesBinding, QuesMainViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_main_ques
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun initView() {
        super.initView()

    }

}