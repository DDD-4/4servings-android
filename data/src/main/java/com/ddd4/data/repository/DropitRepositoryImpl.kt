package com.ddd4.data.repository

import com.ddd4.data.mapper.DropitListMapper
import com.ddd4.data.mapper.DropitDataMapper
import com.ddd4.data.source.DropitLocalDataSource
import com.ddd4.domain.entity.DropitDomainEntity
import com.ddd4.domain.repository.DropitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DropitRepositoryImpl(
    private val dropitLocalDataSource: DropitLocalDataSource,
    private val dropitListMapper: DropitListMapper,
    private val dropitDataMapper: DropitDataMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

): DropitRepository {

    override suspend fun insert(dropitDomainEntity: DropitDomainEntity) {
        dropitLocalDataSource.insert(dropitDataMapper.toDataEntity(dropitDomainEntity))
    }

    override suspend fun update(dropitDomainEntity: DropitDomainEntity) {
        dropitLocalDataSource.update(dropitDataMapper.toDataEntity(dropitDomainEntity))
    }

    override suspend fun delete(dropitDomainEntity: DropitDomainEntity) {
        dropitLocalDataSource.delete(dropitDataMapper.toDataEntity(dropitDomainEntity))
    }

    override suspend fun getAllData(): Flow<List<DropitDomainEntity>> {
        return withContext(ioDispatcher){
            flow {
                emit(dropitListMapper.toDomainEntity(dropitLocalDataSource.getAllData()))
            }
        }
    }
}
