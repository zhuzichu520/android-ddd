package com.zhuzichu.shared.ext

import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupWithViewPager(viewPager: ViewPager2) {
    viewPager.registerOnPageChangeCallback(
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                this@setupWithViewPager.menu.getItem(position).isChecked = true
            }
        }
    )
    this.setOnItemSelectedListener {
        this@setupWithViewPager.menu.forEachIndexed { index, item ->
            if (it.itemId == item.itemId) {
                viewPager.setCurrentItem(index, false)
            }
        }
        true
    }
}