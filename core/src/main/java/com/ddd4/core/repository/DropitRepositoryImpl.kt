package com.ddd4.core.repository

import com.ddd4.core.room.DropitDao
import com.ddd4.model.entity.DropitEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DropitRepositoryImpl(private val dropitDao: DropitDao)
    :DropitRepository {

    override suspend fun insert(dropitEntity: DropitEntity) {
        dropitDao.insert(dropitEntity)
    }

    override suspend fun update(dropitEntity: DropitEntity) {
        dropitDao.update(dropitEntity)
    }

    override suspend fun delete(dropitEntity: DropitEntity) {
        dropitDao.delete(dropitEntity)
    }

    override suspend fun getAllData()= flow<List<DropitEntity>>{
        dropitDao.getAllData()
    }.flowOn(Dispatchers.IO)
}
