package com.zhuzichu.wan

import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

}