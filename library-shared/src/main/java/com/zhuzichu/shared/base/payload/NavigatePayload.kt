package com.zhuzichu.shared.base.payload

import android.os.Bundle
import androidx.navigation.NavOptions

internal data class NavigatePayload(
    val resId: Int,
    val args: Bundle?,
    val navOptions: NavOptions?
)
