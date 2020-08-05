package com.ddd4.domain.repository

import com.ddd4.domain.entity.DomainModel
import kotlinx.coroutines.flow.Flow

interface DropitRepository: Repository {

    suspend fun insert(DomainModel: DomainModel)

    suspend fun update(DomainModel: DomainModel)

    suspend fun delete(DomainModel: DomainModel)

    suspend fun getAllData(): Flow<List<DomainModel>>
}
