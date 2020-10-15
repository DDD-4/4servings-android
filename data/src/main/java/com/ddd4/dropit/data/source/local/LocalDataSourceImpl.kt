package com.ddd4.dropit.data.source.local

import android.content.Context
import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.data.mapper.mapToData
import com.ddd4.dropit.data.mapper.mapToDomain
import com.ddd4.dropit.data.source.local.model.Category
import com.ddd4.dropit.data.source.local.preferences.SharedPrefHelper
import com.ddd4.dropit.data.source.local.room.DatabaseDao
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.*

class LocalDataSourceImpl(
    private val databaseDao: DatabaseDao,
    private val sharedPrefHelper: SharedPrefHelper,
    private val context: Context
): LocalDataSource {

    override suspend fun setSectionFromJson(): Result<Unit> =
        coroutineScope {
            try {
                val jsonFileString = context.assets
                    .open("category.json")
                    .bufferedReader()
                    .use { it.readText() }

                val gson = Gson()
                val sectionsType = object : TypeToken<List<Category>>() {}.type
                val sections: List<Category> = gson.fromJson(jsonFileString, sectionsType)

                val result = databaseDao.insertSection(sections)

                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getCategoryItems(): Result<List<DomainEntity.Category>> =
        coroutineScope {
            try {
                val result = databaseDao.selectCategories().map(DataEntity.Category::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getSubCategoryItems(id: Long): Result<List<DomainEntity.SubCategory>> =
        coroutineScope {
            try {
                val result = databaseDao.selectSubCategories(id).map(DataEntity.SubCategory::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getFolderItems(): Result<List<DomainEntity.Folder>> =
        coroutineScope {
            try {
                val result = databaseDao.selectFolders().map(DataEntity.Folder::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun setItem(item: DataEntity.Item): Result<Long> =
        coroutineScope {
            try {
                if (databaseDao.selectFolders().isNullOrEmpty()) {
                    databaseDao.insertFolder(DataEntity.Folder(name = "최근항목", createAt = Date()))
                }

                val result = databaseDao.insertItem(item)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getItemsByFolder(folderId: Long): Result<List<DomainEntity.Item>> =
        coroutineScope {
            try {
                val result =
                    databaseDao.selectItemsByFolder(folderId).map(DataEntity.Item::mapToDomain)

                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getFolderByName(folderName: String): Result<DomainEntity.Folder> =
        coroutineScope {
            try {
                val result = databaseDao.selectFolderByName(folderName).mapToDomain()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getFolderById(folderId: Long): Result<DomainEntity.Folder> =
        coroutineScope {
            try {
                val result = databaseDao.selectFolderById(folderId).mapToDomain()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getItemsByCategory(categoryId: Long): Result<List<DomainEntity.Item>> =
        coroutineScope {
            try {
                val result = databaseDao.selectItemsByCategory(categoryId).map(DataEntity.Item::mapToDomain)
                Result.Success(result)
            } catch (e: Exception){
                Result.Error(e)
            }
        }

    override suspend fun getCategoryById(categoryId: Long): Result<DomainEntity.Category> =
        coroutineScope {
            try {
                val result = databaseDao.selectCategoryById(categoryId).mapToDomain()
                Result.Success(result)
            } catch (e: Exception){
                Result.Error(e)
            }
        }


    override suspend fun getDetailItem(itemId: Long): Result<DomainEntity.Item> =
        coroutineScope {
            try {
                val result = databaseDao.selectItem(itemId).mapToDomain()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getAlarmIds(): Result<List<DomainEntity.Item>> =
        withContext(Dispatchers.IO) {
            try {
                val result = databaseDao.selectItemAlarmIds().map(DataEntity.Item::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun updateItem(item: DomainEntity.Item): Result<Unit> =
        coroutineScope {
            try {
                databaseDao.updateItem(item.mapToData())
                Result.Success(Unit)
            } catch (e: Exception){
                Result.Error(e)
            }
        }

    override suspend fun createFolder(folder: DomainEntity.Folder): Result<Unit> =
        coroutineScope {
            try {
                databaseDao.insertFolder(folder.mapToData())
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun deleteItem(itemId: Long): Result<Unit> =
        coroutineScope {
            try {
                databaseDao.deleteItem(itemId)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun updateItemByFolderId(folderId: Long, itemId: Long): Result<Unit> =
        coroutineScope {
            try {
                databaseDao.updateItemByFolderId(folderId, itemId)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}