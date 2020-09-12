package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.repository.DropitRepository

class DeleteItemUseCase(
    private val dropitRepository: DropitRepository
){
    suspend operator fun invoke(
        itemId: Long
    ): Result<Unit> = dropitRepository.deleteItem(itemId)
}