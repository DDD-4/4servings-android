package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import java.lang.Exception

class GetFoldersUseCase(
    private val dropitRepository: DropitRepository
) {
    suspend operator fun invoke(
    ): Result<List<DomainEntity.Folder>> =
        dropitRepository.getFolderItems()
}
