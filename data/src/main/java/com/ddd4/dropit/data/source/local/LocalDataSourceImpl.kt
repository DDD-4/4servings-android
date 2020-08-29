package com.ddd4.dropit.data.source.local

import android.content.Context
import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.data.mapper.mapToDomain
import com.ddd4.dropit.data.source.local.model.Category
import com.ddd4.dropit.data.source.local.preferences.SharedPrefHelper
import com.ddd4.dropit.data.source.local.room.DatabaseDao
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class LocalDataSourceImpl(
    private val databaseDao: DatabaseDao,
    private val sharedPrefHelper: SharedPrefHelper,
    private val context: Context
): LocalDataSource {

    override suspend fun setSectionFromJson(): Result<Unit> =
        withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.IO) {
            try {
                val result = databaseDao.selectCategories().map(DataEntity.Category::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getSubCategoryItems(id: Long): Result<List<DomainEntity.SubCategory>> =
        withContext(Dispatchers.IO) {
            try {
                val result = databaseDao.selectSubCategories(id).map(DataEntity.SubCategory::mapToDomain)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getFolderItems(): Result<List<DomainEntity.Folder>> =
        withContext(Dispatchers.IO) {
            try {
                if (databaseDao.selectFolders().isNullOrEmpty()) {
                    databaseDao.insertFolder(DataEntity.Folder(name = "최근항목", createAt = Date()))
                }

                val result = databaseDao.selectFolders().map(DataEntity.Folder::mapToDomain)

                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun setItem(item: DataEntity.Item): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val result = databaseDao.insertItem(item)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}