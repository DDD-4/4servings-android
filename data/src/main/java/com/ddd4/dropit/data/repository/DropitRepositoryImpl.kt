package com.ddd4.dropit.data.repository

import com.ddd4.dropit.data.source.local.LocalDataSource
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class DropitRepositoryImpl(
    private val localDataSource: LocalDataSource
): DropitRepository {

    override suspend fun getFolders(): List<DomainEntity.Folder> =
        localDataSource.getFolders()

    override suspend fun getItems(folderId: Long): List<DomainEntity.Item> =
        localDataSource.getItems(folderId)
}
