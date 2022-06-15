package com.zhuzichu.wan.profile

import android.util.Log
import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.wan.R
import com.zhuzichu.wan.databinding.FragmentMainProfileBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProfileMainFragment"

@AndroidEntryPoint
class ProfileMainFragment : BaseFragment<FragmentMainProfileBinding, ProfileMainViewModel>() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_main_profile
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun initView() {
        super.initView()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

}