package com.ddd4.domain.repository

import com.ddd4.domain.entity.DropitDomainEntity
import kotlinx.coroutines.flow.Flow

interface DropitRepository: Repository {

    suspend fun insert(dropitDomainEntity: DropitDomainEntity)

    suspend fun update(dropitDomainEntity: DropitDomainEntity)

    suspend fun delete(dropitDomainEntity: DropitDomainEntity)

    suspend fun getAllData(): Flow<List<DropitDomainEntity>>
}
