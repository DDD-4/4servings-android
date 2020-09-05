package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class GetFolderByNameUseCase(
    private val dropitRepository: DropitRepository
){
    suspend operator fun invoke(
        folderName: String
    ): Result<DomainEntity.Folder> = dropitRepository.getFolderByName(folderName)
}