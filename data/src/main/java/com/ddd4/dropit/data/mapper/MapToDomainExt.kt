package com.ddd4.dropit.data.mapper

import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.domain.entity.DomainEntity

fun DataEntity.Folder.mapToDomain(): DomainEntity.Folder =
    DomainEntity.Folder(id!!, name, createAt, updateAt)

fun DataEntity.Item.mapToDomain(): DomainEntity.Item =
    DomainEntity.Item(id, folderId, categoryId, subCategoryId, alarmTime, name, image, startAt, endAt, createAt, updateAt)

fun DataEntity.Category.mapToDomain(): DomainEntity.Category =
    DomainEntity.Category(id, title)

fun DataEntity.SubCategory.mapToDomain(): DomainEntity.SubCategory =
    DomainEntity.SubCategory(id, endAt, title)