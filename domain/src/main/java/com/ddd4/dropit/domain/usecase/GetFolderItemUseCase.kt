package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.Result

class GetFolderItemUseCase(
    private val dropitRepository: DropitRepository
){
    suspend operator fun invoke(
        folderId: Long
    ): Result<List<DomainEntity.Item>> = dropitRepository.getItemsByFolder(folderId)
}