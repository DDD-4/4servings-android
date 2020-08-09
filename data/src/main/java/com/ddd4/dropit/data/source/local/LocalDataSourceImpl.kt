package com.ddd4.dropit.data.source.local

import com.ddd4.dropit.data.mapper.mapToDomain
import com.ddd4.dropit.data.source.local.preferences.SharedPrefHelper
import com.ddd4.dropit.data.source.local.room.DatabaseDao
import com.ddd4.dropit.domain.entity.DomainEntity

class LocalDataSourceImpl(
    private val databaseDao: DatabaseDao,
    private val sharedPrefHelper: SharedPrefHelper
): LocalDataSource {

    override suspend fun getFolders(): List<DomainEntity.Folder> =
        databaseDao.selectFolders().map { data ->
            data.mapToDomain()
        }

    override suspend fun getItems(folderId: Long): List<DomainEntity.Item> =
        databaseDao.selectItems(folderId).map { data ->
            data.mapToDomain()
        }
}