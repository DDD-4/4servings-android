package com.ddd4.dropit.domain.repository

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity

interface DropitRepository {

    suspend fun setSectionFromJson(): Result<Unit>

    suspend fun getCategoryItems(): Result<List<DomainEntity.Category>>

    suspend fun getSubCategoryItems(id: Long): Result<List<DomainEntity.SubCategory>>

    suspend fun getFolderItems(): Result<List<DomainEntity.Folder>>

    suspend fun getFolderByName(folderName: String): Result<DomainEntity.Folder>

    suspend fun getFolderById(folderId: Long): Result<DomainEntity.Folder>

    suspend fun getCategoryById(categoryId: Long): Result<DomainEntity.Category>

    suspend fun setItem(item: DomainEntity.Item): Result<Long>

    suspend fun getAlarmIds(): Result<List<DomainEntity.Item>>

    suspend fun getItemsByFolder(folderId: Long): Result<List<DomainEntity.Item>>

    suspend fun getItemsByCategory(categoryId: Long): Result<List<DomainEntity.Item>>

    suspend fun getDetailItem(itemId: Long): Result<DomainEntity.Item>

    suspend fun updateItem(item: DomainEntity.Item): Result<Unit>

    suspend fun createFolder(folder: DomainEntity.Folder): Result<Unit>

    suspend fun deleteItem(itemId: Long): Result<Unit>

    suspend fun updateItemByFolderId(folderId: Long, itemId: Long): Result<Unit>

}
