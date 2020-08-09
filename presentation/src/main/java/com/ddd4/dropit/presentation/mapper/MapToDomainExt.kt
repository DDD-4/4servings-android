package com.ddd4.dropit.presentation.mapper

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.presentation.entity.PresentationEntity

fun PresentationEntity.Folder.mapToDomain(): DomainEntity.Folder =
    DomainEntity.Folder(id, name, thumbnail, createAt, updateAt)

fun PresentationEntity.Item.mapToDomain(): DomainEntity.Item =
    DomainEntity.Item(id, folderId, categoryId, name, image, startAt, endAt, createAt, updateAt)