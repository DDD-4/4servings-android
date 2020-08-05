package com.ddd4.data.source

import com.ddd4.data.entity.DropitDataModel

interface DropitLocalDataSource: BaseDataSource {

    suspend fun insert(dropitDataModel: DropitDataModel)

    suspend fun update(dropitDataModel: DropitDataModel)

    suspend fun delete(dropitDataModel: DropitDataModel)

    suspend fun getAllData(): List<DropitDataModel>
}