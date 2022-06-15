package com.zhuzichu.shared.base.payload

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

internal data class NavigatePayload(
    val resId: Int,
    val args: Bundle?,
    val navOptions: NavOptions?,
    val navigatorExtras: Navigator.Extras?
)
