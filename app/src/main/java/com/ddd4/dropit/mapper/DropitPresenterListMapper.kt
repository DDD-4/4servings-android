package com.ddd4.dropit.mapper

import com.ddd4.domain.entity.DomainModel
import com.ddd4.dropit.entity.PresenterModel
import javax.inject.Inject

open class DropitPresenterListMapper @Inject constructor():
    PresenterMapper<List<PresenterModel>, List<DomainModel>> {

    override fun toPresenterEntity(type: List<DomainModel>): List<PresenterModel> {
        return type.map{
            PresenterModel(it.id, it.product, it.expirationDate, it.dday)
        }    }

    override fun toDomainEntity(type: List<PresenterModel>): List<DomainModel> {
        return type.map {
            DomainModel(it.id, it.product, it.expirationDate, it.dday)
        }
    }
}