package com.zhuzichu.shared.command

class BindingCommand<T>(
    private var execute: (() -> Unit)? = null,
    private var consumer: ((T?) -> Unit)? = null,
    private var canExecute: Boolean = true
) {
    fun execute() {
        if (canExecute) {
            execute?.invoke()
        }
    }

    fun execute(parameter: T?) {
        if (canExecute) {
            consumer?.invoke(parameter)
        }
    }

}