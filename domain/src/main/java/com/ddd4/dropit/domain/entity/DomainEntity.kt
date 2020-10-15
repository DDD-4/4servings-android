package com.ddd4.dropit.domain.entity

import java.util.*

sealed class DomainEntity {

    data class Folder (
        var id: Long? = 0,
        var name: String,
        var createAt: Date?,
        var updateAt: Date? = Date()
    ): DomainEntity()

    data class Item (
        var id: Long? = 0,
        var folderId: Long? = 0,
        var categoryId: Long,
        var subCategoryId: Long,
        var alarmTime: Long,
        var name: String,
        var image: String,
        var startAt: Date,
        var endAt: Date,
        var createAt: Date?,
        var updateAt: Date? = Date()
    ): DomainEntity()

    data class FolderAndItems (
        val folder: Folder,
        var items: List<Item> = ArrayList()
    ): DomainEntity()

    data class Category(
        val id: Long,
        val title: String
    ): DomainEntity()

    data class SubCategory(
        val id: Long,
        var endAt: Int,
        val title: String
    ): DomainEntity()
}