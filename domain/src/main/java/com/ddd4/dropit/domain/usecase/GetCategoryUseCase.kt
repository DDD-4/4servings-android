package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository
import java.lang.Exception

class GetCategoryUseCase(
    private val dropitRepository: DropitRepository
) {
    suspend operator fun invoke(
    ): Result<List<DomainEntity.Category>> =
        dropitRepository.getCategoryItems()
}