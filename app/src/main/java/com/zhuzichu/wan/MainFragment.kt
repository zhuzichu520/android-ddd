package com.zhuzichu.wan

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhuzichu.shared.base.BaseFragment
import com.zhuzichu.shared.ext.setupWithViewPager
import com.zhuzichu.wan.databinding.FragmentMainBinding
import com.zhuzichu.wan.home.HomeMainFragment
import com.zhuzichu.wan.navi.NaviMainFragment
import com.zhuzichu.wan.profile.ProfileMainFragment
import com.zhuzichu.wan.ques.QuesMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    private val fragments = listOf(
        HomeMainFragment(),
        QuesMainFragment(),
        NaviMainFragment(),
        ProfileMainFragment()
    )

    override fun setLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun initView() {
        super.initView()
        binding.viewpagerNavi.offscreenPageLimit = fragments.size
        binding.viewpagerNavi.adapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        binding.bottomNavi.setupWithViewPager(binding.viewpagerNavi)
    }

}