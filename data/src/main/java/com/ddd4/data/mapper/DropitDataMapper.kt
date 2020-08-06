package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataModel
import com.ddd4.domain.entity.DomainModel
import javax.inject.Inject

open class DropitDataMapper @Inject constructor(): DataMapper<DropitDataModel, DomainModel> {
    override fun toDomainEntity(type: DropitDataModel): DomainModel {
        return DomainModel(type.id, type.product, type.expirationDate, type.dday)
    }

    override fun toDataEntity(type: DomainModel): DropitDataModel {
        return DropitDataModel(type.id, type.product, type.expirationDate, type.dday)
    }
}