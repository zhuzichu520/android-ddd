@file: JvmName("ToolObject")
@file:Suppress("unused")

package com.zhuzichu.shared.tool

fun Any.className(): String {
    return this.javaClass.simpleName
}

inline fun <reified T : Any> new(): T {
    val clz = T::class.java
    val mCreate = clz.getDeclaredConstructor()
    mCreate.isAccessible = true
    return mCreate.newInstance()
}

fun Any.diffEquals(item: Any): Boolean {
    return this == item
}

