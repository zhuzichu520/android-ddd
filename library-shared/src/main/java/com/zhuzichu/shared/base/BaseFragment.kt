package com.zhuzichu.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.zhuzichu.shared.exception.BizException
import com.zhuzichu.shared.tool.toCast
import com.zhuzichu.shared.view.LoadingDialog
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<TBinding : ViewDataBinding, TViewModel : BaseViewModel> : Fragment() {

    lateinit var binding: TBinding

    lateinit var viewModel: TViewModel

    abstract fun setLayoutId(): Int

    abstract fun bindVariableId(): Int

    open fun initVariable() {}

    open fun initView() {}

    open fun initData() {}

    private var loading: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, setLayoutId(), null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewDataBinding()
        registerUIChangeLiveDataCallback()
        initVariable()
        initView()
        initData()
    }

    private fun registerUIChangeLiveDataCallback() {
        viewModel.onToastEvent.observe(viewLifecycleOwner) {
            toast(it)
        }
        viewModel.onLoadingEvent.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.onExceptionEvent.observe(viewLifecycleOwner) {
            handleException(it)
        }
        viewModel.onNavigateEvent.observe(viewLifecycleOwner) {
            navigate(it.resId, it.args, it.navOptions)
        }
    }

    private fun initViewDataBinding() {
        val type = this::class.java.genericSuperclass
        val modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1]
        } else {
            BaseViewModel::class.java
        }
        viewModel = ViewModelProvider(this).get(modelClass.toCast())
        binding.setVariable(bindVariableId(), viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }


    open fun toast(text: String?) {
        Toast.makeText(requireContext(), text ?: "", Toast.LENGTH_SHORT).show()
    }

    open fun showLoading() {
        if (loading == null) {
            loading = LoadingDialog(requireContext())
        }
        loading?.show()
    }

    open fun hideLoading() {
        if (loading == null) {
            loading = LoadingDialog(requireContext())
        }
        loading?.dismiss()
    }

    open fun handleException(e: Exception) {
        if (e is BizException) {
            viewModel.toast(e.message)
        } else {
            viewModel.toast(e.message)
        }
    }

    open fun navigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
        findNavController().navigate(resId, args, navOptions)
    }


}