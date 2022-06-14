package com.zhuzichu.wan

import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

}