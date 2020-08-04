package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataEntity
import com.ddd4.domain.entity.DropitDomainEntity
import javax.inject.Inject

open class DropitDataMapper @Inject constructor(): DataMapper<DropitDataEntity, DropitDomainEntity> {
    override fun toDomainEntity(type: DropitDataEntity): DropitDomainEntity {
        return DropitDomainEntity(type.id, type.product, type.expirationDate, type.dday)
    }

    override fun toDataEntity(type: DropitDomainEntity): DropitDataEntity {
        return DropitDataEntity(type.id, type.product, type.expirationDate, type.dday)
    }
}