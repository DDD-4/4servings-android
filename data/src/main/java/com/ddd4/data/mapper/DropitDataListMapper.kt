package com.ddd4.data.mapper

import com.ddd4.data.entity.DropitDataModel
import com.ddd4.domain.entity.DomainModel
import javax.inject.Inject

open class DropitDataListMapper @Inject constructor(): DataMapper<List<DropitDataModel>, List<DomainModel>> {
    override fun toDomainEntity(type: List<DropitDataModel>): List<DomainModel> {
        return type.map{
            DomainModel(it.id, it.product, it.expirationDate, it.dday)
        }
    }

    override fun toDataEntity(type: List<DomainModel>): List<DropitDataModel> {
        return type.map{
            DropitDataModel(it.id, it.product, it.expirationDate, it.dday)
        }

    }
}