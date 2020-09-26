package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class GetCategoryByIdUseCase(
    private val dropitRepository: DropitRepository
){
    suspend operator fun invoke(
        categoryId: Long
    ): Result<DomainEntity.Category> = dropitRepository.getCategoryById(categoryId)
}