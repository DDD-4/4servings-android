package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class GetCategoryItemUseCase(
    private val dropitRepository: DropitRepository
){
    suspend operator fun invoke(
        categoryId: Long
    ): Result<List<DomainEntity.Item>> = dropitRepository.getItemsByCategory(categoryId)
}