package com.ddd4.dropit.presentation.entity

import java.util.*

sealed class PresentationEntity {

    data class Folder (
        var id: Long,
        var name: String,
        var createAt: Date,
        var updateAt: Date? = Date()
    ): PresentationEntity()

    data class Item (
        var id: Long? = 0,
        var folderId: Long? = 0,
        var categoryId: Long,
        var subCategoryId: Long,
        var name: String,
        var image: String,
        var startAt: Date,
        var endAt: Date,
        var createAt: Date?,
        var updateAt: Date? = Date()
    ): PresentationEntity()

    data class Category (
        var id: Long,
        var title: String
    ): PresentationEntity()

    data class SubCategory(
        val id: Long,
        var endAt: Int,
        val title: String
    ): PresentationEntity()
}