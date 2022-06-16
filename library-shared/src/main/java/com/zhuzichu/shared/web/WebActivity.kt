package com.zhuzichu.shared.web

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.zhuzichu.shared.R

class WebActivity : AppCompatActivity(R.layout.activity_web) {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, WebActivity::class.java)
            context.startActivity(starter)
        }
    }

}