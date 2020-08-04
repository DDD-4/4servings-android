package com.ddd4.domain.usecases.dropit

import com.ddd4.domain.entity.DropitDomainEntity
import com.ddd4.domain.repository.DropitRepository

class GetDummyUseCase(private val dropitRepository: DropitRepository){
    suspend operator fun invoke(domainEntity: DropitDomainEntity){
        return dropitRepository.insert(domainEntity)
    }
}