package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.Result

class CreateFolderUseCase(
    private val dropitRepository: DropitRepository
) {
    suspend operator fun invoke(
        folder: DomainEntity.Folder
    ): Result<Unit> = dropitRepository.createFolder(folder)
}