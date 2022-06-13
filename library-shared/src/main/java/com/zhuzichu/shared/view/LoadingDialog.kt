package com.zhuzichu.shared.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.zhuzichu.shared.R

class LoadingDialog(context: Context) : AlertDialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading)
    }

}