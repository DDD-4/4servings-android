package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataEntity
import com.ddd4.domain.entity.DropitDomainEntity
import javax.inject.Inject

open class DropitListMapper @Inject constructor(): DataMapper<List<DropitDataEntity>, List<DropitDomainEntity>> {
    override fun toDomainEntity(type: List<DropitDataEntity>): List<DropitDomainEntity> {
        return type.map{
            DropitDomainEntity(it.id, it.product, it.expirationDate, it.dday)
        }
    }

    override fun toDataEntity(type: List<DropitDomainEntity>): List<DropitDataEntity> {
        return type.map{
            DropitDataEntity(it.id, it.product, it.expirationDate, it.dday)
        }

    }
}