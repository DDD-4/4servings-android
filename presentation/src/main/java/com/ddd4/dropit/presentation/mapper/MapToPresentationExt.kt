package com.ddd4.dropit.presentation.mapper

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.presentation.entity.PresentationEntity

fun DomainEntity.Folder.mapToPresentation(): PresentationEntity.Folder =
    PresentationEntity.Folder(id, name, thumbnail, createAt, updateAt)

fun DomainEntity.Item.mapToPresentation(): PresentationEntity.Item =
    PresentationEntity.Item(id, folderId, categoryId, name, image, startAt, endAt, createAt, updateAt)