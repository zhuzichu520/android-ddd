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
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.zhuzichu.shared.R
import com.zhuzichu.shared.exception.BizException
import com.zhuzichu.shared.tool.toCast
import com.zhuzichu.shared.view.LoadingDialog
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
        viewModel.viewLifecycleOwner = viewLifecycleOwner
        binding.setVariable(bindVariableId(), viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        lifecycle.addObserver(viewModel)
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
        when (e) {
            is BizException -> {
                viewModel.toast(e.message)
            }
            is SocketTimeoutException,
            is UnknownHostException,
            is ConnectException
            -> {
                viewModel.toast("网络异常,请稍后再试")
            }
            else -> {
                viewModel.toast(e.message)
            }
        }
    }

    open fun navigate(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        findNavController().navigate(resId, args, toNavOptions(resId), navigatorExtras)
    }

    private fun toNavOptions(@IdRes resId: Int): NavOptions? {
        val navOptions =
            findNavController().currentDestination?.getAction(resId)?.navOptions ?: return null
        return androidx.navigation.navOptions {
            anim {
                enter = R.anim.slide_in
                exit = R.anim.fade_out
                popEnter = R.anim.fade_in
                popExit = R.anim.slide_out
            }
            launchSingleTop = navOptions.shouldLaunchSingleTop()
            popUpTo(navOptions.popUpToId) {
                this.inclusive = navOptions.isPopUpToInclusive()
            }
        }
    }

}