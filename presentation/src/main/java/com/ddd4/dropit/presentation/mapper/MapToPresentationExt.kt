package com.ddd4.dropit.presentation.mapper

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.presentation.entity.PresentationEntity

fun DomainEntity.Folder.mapToPresentation(): PresentationEntity.Folder =
    PresentationEntity.Folder(id!!, name, createAt!!, updateAt)

fun DomainEntity.Item.mapToPresentation(): PresentationEntity.Item =
    PresentationEntity.Item(id, folderId, categoryId, subCategoryId, alarmTime, name, image, startAt, endAt, createAt, updateAt)

fun DomainEntity.Category.mapToPresentation(): PresentationEntity.Category =
    PresentationEntity.Category(id, title)

fun DomainEntity.SubCategory.mapToPresentation(): PresentationEntity.SubCategory =
    PresentationEntity.SubCategory(id, endAt, title)