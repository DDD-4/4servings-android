package com.ddd4.domain.usecases.usecaseImpl

import com.ddd4.domain.entity.DomainModel
import com.ddd4.domain.repository.DropitRepository
import com.ddd4.domain.usecases.usecase.GetDummyUseCase

class GetDummyUseCaseImpl(
    private val dropitRepository: DropitRepository
): GetDummyUseCase {
    override suspend operator fun invoke(domainModel: DomainModel){
        return dropitRepository.insert(domainModel)
    }
}