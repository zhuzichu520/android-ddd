package com.zhuzichu.wan.navi

import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.R
import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.wan.databinding.FragmentMainNaviBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NaviMainFragment : BaseFragment<FragmentMainNaviBinding, NaviMainViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_main_navi
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun initView() {
        super.initView()

    }

}