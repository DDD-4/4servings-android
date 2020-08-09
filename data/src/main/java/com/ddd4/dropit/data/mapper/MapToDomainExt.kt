package com.ddd4.dropit.data.mapper

import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.domain.entity.DomainEntity

fun DataEntity.Folder.mapToDomain(): DomainEntity.Folder =
    DomainEntity.Folder(id, name, thumbnail, createAt, updateAt)

fun DataEntity.Item.mapToDomain(): DomainEntity.Item =
    DomainEntity.Item(id, folderId, categoryId, name, image, startAt, endAt, createAt, updateAt)