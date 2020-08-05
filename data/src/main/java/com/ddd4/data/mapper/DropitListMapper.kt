package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataModel
import com.ddd4.domain.entity.DropitDomainModel
import javax.inject.Inject

open class DropitListMapper @Inject constructor(): DataMapper<List<DropitDataModel>, List<DropitDomainModel>> {
    override fun toDomainEntity(type: List<DropitDataModel>): List<DropitDomainModel> {
        return type.map{
            DropitDomainModel(it.id, it.product, it.expirationDate, it.dday)
        }
    }

    override fun toDataEntity(type: List<DropitDomainModel>): List<DropitDataModel> {
        return type.map{
            DropitDataModel(it.id, it.product, it.expirationDate, it.dday)
        }

    }
}