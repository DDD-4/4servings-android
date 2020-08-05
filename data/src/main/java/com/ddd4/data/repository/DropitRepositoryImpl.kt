package com.ddd4.data.repository

import com.ddd4.data.mapper.DropitDataListMapper
import com.ddd4.data.mapper.DropitDataMapper
import com.ddd4.data.source.DropitLocalDataSource
import com.ddd4.domain.entity.DomainModel
import com.ddd4.domain.repository.DropitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DropitRepositoryImpl(
    private val dropitLocalDataSource: DropitLocalDataSource,
    private val dropitDataListMapper: DropitDataListMapper,
    private val dropitDataMapper: DropitDataMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

): DropitRepository {

    override suspend fun insert(DomainModel: DomainModel) {
        dropitLocalDataSource.insert(dropitDataMapper.toDataEntity(DomainModel))
    }

    override suspend fun update(DomainModel: DomainModel) {
        dropitLocalDataSource.update(dropitDataMapper.toDataEntity(DomainModel))
    }

    override suspend fun delete(DomainModel: DomainModel) {
        dropitLocalDataSource.delete(dropitDataMapper.toDataEntity(DomainModel))
    }

    override suspend fun getAllData(): Flow<List<DomainModel>> {
        return withContext(ioDispatcher){
            flow {
                emit(dropitDataListMapper.toDomainEntity(dropitLocalDataSource.getAllData()))
            }
        }
    }
}
