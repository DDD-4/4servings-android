package com.ddd4.domain.repository

import com.ddd4.domain.entity.DropitDomainModel
import kotlinx.coroutines.flow.Flow

interface DropitRepository: Repository {

    suspend fun insert(dropitDomainModel: DropitDomainModel)

    suspend fun update(dropitDomainModel: DropitDomainModel)

    suspend fun delete(dropitDomainModel: DropitDomainModel)

    suspend fun getAllData(): Flow<List<DropitDomainModel>>
}
