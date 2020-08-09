package com.ddd4.dropit.domain.entity

import java.util.*

sealed class DomainEntity {

    data class Folder (
        var id: Long,
        var name: String,
        var thumbnail: String,
        var createAt: Date,
        var updateAt: Date? = Date()
    ): DomainEntity()

    data class Item (
        var id: Long,
        var folderId: Long? = 0,
        var categoryId: Long,
        var name: String,
        var image: String,
        var startAt: Date,
        var endAt: Date,
        var createAt: Date,
        var updateAt: Date? = Date()
    ): DomainEntity()

    data class FolderAndItems (
        val folder: Folder,
        var items: List<Item> = ArrayList()
    ): DomainEntity()
}