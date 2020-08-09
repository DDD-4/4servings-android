package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository
import java.lang.Exception

class getItemUseCase(
    private val dropitRepository: DropitRepository
) {

    suspend fun execute(folderId: Long): Result =
        try {
            dropitRepository.getItems(folderId).let { data ->
                Result.Success(data)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    sealed class Result {
        data class Success(val data: List<DomainEntity.Item>) : Result()
        data class Error(val e: Throwable) : Result()
    }
}