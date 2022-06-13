package com.zhuzichu.shared.command

class BindingCommand(
    private var execute: (() -> Unit),
    private var canExecute: Boolean = true
) {
    fun execute() {
        if (canExecute) {
            execute.invoke()
        }
    }
}