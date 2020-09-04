package com.ddd4.dropit.data.source.local

import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity

interface LocalDataSource {

    suspend fun setSectionFromJson(): Result<Unit>

    suspend fun getCategoryItems(): Result<List<DomainEntity.Category>>

    suspend fun getSubCategoryItems(id: Long): Result<List<DomainEntity.SubCategory>>

    suspend fun getFolderItems(): Result<List<DomainEntity.Folder>>

    suspend fun setItem(item: DataEntity.Item): Result<Unit>

    suspend fun getAlarmIds(): Result<List<DomainEntity.Item>>

    suspend fun getItemsByFolder(folderId: Long): Result<List<DomainEntity.Item>>

    suspend fun getItemsByCategory(categoryId: Long): Result<List<DomainEntity.Item>>

    suspend fun getDetailItem(itemId: Long): Result<DomainEntity.Item>

    suspend fun updateItem(item: DomainEntity.Item): Result<Unit>
}