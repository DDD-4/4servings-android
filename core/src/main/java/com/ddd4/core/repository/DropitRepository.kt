package com.ddd4.core.repository

import com.ddd4.model.entity.DropitEntity
import kotlinx.coroutines.flow.Flow

interface DropitRepository {

    suspend fun insert(dropitEntity: DropitEntity)

    suspend fun update(dropitEntity: DropitEntity)

    suspend fun delete(dropitEntity: DropitEntity)

    suspend fun getAllData(): Flow<List<DropitEntity>>
}
