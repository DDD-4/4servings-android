package com.ddd4.domain.repository

import com.ddd4.domain.entity.DropitEntity
import kotlinx.coroutines.flow.Flow

interface DropitRepository {

    suspend fun insert(dropitEntity: DropitEntity)

    suspend fun update(dropitEntity: DropitEntity)

    suspend fun delete(dropitEntity: DropitEntity)

    suspend fun getAllData(): Flow<List<DropitEntity>>
}
