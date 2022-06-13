package com.zhuzichu.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            Toast.makeText(requireContext(), it ?: "", Toast.LENGTH_SHORT).show()
        }
        viewModel.onLoadingEvent.observe(viewLifecycleOwner) {
            if (loading == null) {
                loading = LoadingDialog(requireContext())
            }
            if (it) {
                loading?.show()
            } else {
                loading?.dismiss()
            }
        }
        viewModel.onExceptionEvent.observe(viewLifecycleOwner) {
            if (it is BizException) {
                viewModel.toast(it.message)
            } else {
                viewModel.toast(it.message)
            }
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

}