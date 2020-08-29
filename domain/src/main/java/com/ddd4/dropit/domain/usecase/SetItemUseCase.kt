package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class SetItemUseCase(
    private val dropitRepository: DropitRepository
) {
    suspend fun execute(item: DomainEntity.Item): Result<Unit> =
        dropitRepository.setItem(item)
}