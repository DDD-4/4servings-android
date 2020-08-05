package com.ddd4.dropit.mapper

import com.ddd4.domain.entity.DomainModel
import com.ddd4.dropit.entity.PresenterModel
import javax.inject.Inject

open class DropitPresenterMapper @Inject constructor(): PresenterMapper<PresenterModel, DomainModel> {

    override fun toPresenterEntity(type: DomainModel): PresenterModel {
        return PresenterModel(type.id, type.product, type.expirationDate, type.dday)
    }

    override fun toDomainEntity(type: PresenterModel): DomainModel {
        return DomainModel(type.id, type.product, type.expirationDate, type.dday)
    }

}