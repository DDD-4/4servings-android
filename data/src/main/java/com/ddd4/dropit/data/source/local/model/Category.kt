package com.ddd4.dropit.data.source.local.model

data class Category(
    val id: Long,
    val title: String,
    var subCategory: List<SubCategory>
)

data class SubCategory(
    val categoryId: Long,
    val endAt: Int,
    val title: String
)