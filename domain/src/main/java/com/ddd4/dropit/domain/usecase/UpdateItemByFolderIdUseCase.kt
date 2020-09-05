package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.repository.DropitRepository

class UpdateItemByFolderIdUseCase(
    private val dropitRepository: DropitRepository
) {
    suspend operator fun invoke(
        folderId: Long,
        itemId: Long
    ): Result<Unit> =
        dropitRepository.updateItemByFolderId(folderId, itemId)
}
