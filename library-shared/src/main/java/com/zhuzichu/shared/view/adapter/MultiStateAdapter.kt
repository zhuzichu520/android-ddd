package com.zhuzichu.shared.view.adapter

import androidx.databinding.BindingAdapter
import com.zy.multistatepage.MultiStateContainer
import com.zy.multistatepage.state.EmptyState
import com.zy.multistatepage.state.ErrorState
import com.zy.multistatepage.state.LoadingState
import com.zy.multistatepage.state.SuccessState

enum class ViewState {
    ERROR, SUCCESS, LOADING, EMPTY
}

@BindingAdapter(value = ["viewState"], requireAll = false)
fun onBindMultiStateContainer(
    container: MultiStateContainer,
    state: ViewState?
) {
    state?.let {
        when (it) {
            ViewState.ERROR -> {
                container.show(ErrorState::class.java) {

                }
            }
            ViewState.LOADING -> {
                container.show(LoadingState::class.java) {

                }
            }
            ViewState.SUCCESS -> {
                container.show(SuccessState::class.java) {

                }
            }
            ViewState.EMPTY -> {
                container.show(EmptyState::class.java) {

                }
            }
        }
    }
}
