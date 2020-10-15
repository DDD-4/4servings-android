package com.ddd4.dropit.data.mapper

import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.domain.entity.DomainEntity

fun DomainEntity.Folder.mapToData(): DataEntity.Folder =
    DataEntity.Folder(id, name, createAt, updateAt)

fun DomainEntity.Item.mapToData(): DataEntity.Item =
    DataEntity.Item(id, folderId, categoryId, subCategoryId, alarmTime, name, image, startAt, endAt, createAt, updateAt)