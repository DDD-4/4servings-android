package com.ddd4.dropit.data.repository

import com.ddd4.dropit.data.mapper.mapToData
import com.ddd4.dropit.data.source.local.LocalDataSource
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DropitRepositoryImpl(
    private val localDataSource: LocalDataSource
): DropitRepository {

    override suspend fun setSectionFromJson(): Result<Unit> =
        localDataSource.setSectionFromJson()

    override suspend fun getCategoryItems(): Result<List<DomainEntity.Category>> =
        localDataSource.getCategoryItems()

    override suspend fun getSubCategoryItems(id: Long): Result<List<DomainEntity.SubCategory>> =
        localDataSource.getSubCategoryItems(id)

    override suspend fun getFolderItems(): Result<List<DomainEntity.Folder>> =
        localDataSource.getFolderItems()

    override suspend fun setItem(item: DomainEntity.Item): Result<Long> =
        localDataSource.setItem(item.mapToData())

    override suspend fun getAlarmIds(): Result<List<DomainEntity.Item>> =
        localDataSource.getAlarmIds()

    override suspend fun getItemsByFolder(folderId: Long): Result<List<DomainEntity.Item>> =
        localDataSource.getItemsByFolder(folderId)

    override suspend fun getItemsByCategory(categoryId: Long): Result<List<DomainEntity.Item>> =
        localDataSource.getItemsByCategory(categoryId)

    override suspend fun getCategoryById(categoryId: Long): Result<DomainEntity.Category> =
        localDataSource.getCategoryById(categoryId)

    override suspend fun getDetailItem(itemId: Long): Result<DomainEntity.Item> =
        localDataSource.getDetailItem(itemId)

    override suspend fun updateItem(item: DomainEntity.Item): Result<Unit> =
        localDataSource.updateItem(item)

    override suspend fun createFolder(folder: DomainEntity.Folder): Result<Unit> =
        localDataSource.createFolder(folder)

    override suspend fun deleteItem(itemId: Long): Result<Unit> =
        localDataSource.deleteItem(itemId)

    override suspend fun updateItemByFolderId(folderId: Long, itemId: Long): Result<Unit> =
        localDataSource.updateItemByFolderId(folderId, itemId)

    override suspend fun getFolderByName(folderName: String): Result<DomainEntity.Folder> =
        localDataSource.getFolderByName(folderName)

    override suspend fun getFolderById(folderId: Long): Result<DomainEntity.Folder> =
        localDataSource.getFolderById(folderId)
}
