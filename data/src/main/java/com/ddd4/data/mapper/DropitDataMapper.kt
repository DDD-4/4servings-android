package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataModel
import com.ddd4.domain.entity.DropitDomainModel
import javax.inject.Inject

open class DropitDataMapper @Inject constructor(): DataMapper<DropitDataModel, DropitDomainModel> {
    override fun toDomainEntity(type: DropitDataModel): DropitDomainModel {
        return DropitDomainModel(type.id, type.product, type.expirationDate, type.dday)
    }

    override fun toDataEntity(type: DropitDomainModel): DropitDataModel {
        return DropitDataModel(type.id, type.product, type.expirationDate, type.dday)
    }
}