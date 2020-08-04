package com.ddd4.data.source

import com.ddd4.data.entity.DropitDataEntity

interface DropitLocalDataSource {

    suspend fun insert(dropitDataEntity: DropitDataEntity)

    suspend fun update(dropitDataEntity: DropitDataEntity)

    suspend fun delete(dropitDataEntity: DropitDataEntity)

    suspend fun getAllData(): List<DropitDataEntity>
}