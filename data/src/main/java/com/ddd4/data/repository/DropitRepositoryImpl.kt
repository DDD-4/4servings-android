package com.ddd4.data.repository

import com.ddd4.data.mapper.DropitListMapper
import com.ddd4.data.mapper.DropitDataMapper
import com.ddd4.data.source.DropitLocalDataSource
import com.ddd4.domain.entity.DropitDomainModel
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

    override suspend fun insert(dropitDomainModel: DropitDomainModel) {
        dropitLocalDataSource.insert(dropitDataMapper.toDataEntity(dropitDomainModel))
    }

    override suspend fun update(dropitDomainModel: DropitDomainModel) {
        dropitLocalDataSource.update(dropitDataMapper.toDataEntity(dropitDomainModel))
    }

    override suspend fun delete(dropitDomainModel: DropitDomainModel) {
        dropitLocalDataSource.delete(dropitDataMapper.toDataEntity(dropitDomainModel))
    }

    override suspend fun getAllData(): Flow<List<DropitDomainModel>> {
        return withContext(ioDispatcher){
            flow {
                emit(dropitListMapper.toDomainEntity(dropitLocalDataSource.getAllData()))
            }
        }
    }
}
