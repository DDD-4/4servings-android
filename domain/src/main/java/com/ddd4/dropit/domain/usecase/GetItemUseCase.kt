package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import java.lang.Exception

class GetItemUseCase(
    private val dropitRepository: DropitRepository
) {

    suspend fun execute(folderId: Long): Result<List<DomainEntity.Item>> =
        try {
            dropitRepository.getItems(folderId).let { data ->
                Result.Success(data)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}