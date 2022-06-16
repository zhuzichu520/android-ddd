package com.zhuzichu.domain.entity

data class Banner(
    var desc: String? = null,
    var id: Int? = null,
    var imagePath: String? = null,
    var isVisible: Int? = null,
    var order: Int? = null,
    var title: String? = null,
    var type: Int? = null,
    var url: String? = null
)