@file:JvmName("ToolCommon")
@file:Suppress("UNCHECKED_CAST")

package com.zhuzichu.shared.tool


/**
 * 类型强转
 *
 */
fun <T> cast(obj: Any): T {
    return obj as T
}

/**
 * 类型强转
 *
 */
fun <T> Any.toCast(): T {
    return this as T
}