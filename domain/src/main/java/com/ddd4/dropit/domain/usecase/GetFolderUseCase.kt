package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import java.lang.Exception

class GetFolderUseCase(
    private val dropitRepository: DropitRepository
) {

    suspend fun execute(): Result<List<DomainEntity.Folder>> =
        try {
            dropitRepository.getFolders().let { data ->
                Result.Success(data)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}
