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

    override suspend fun setItem(item: DomainEntity.Item): Result<Unit> =
        localDataSource.setItem(item.mapToData())
}
