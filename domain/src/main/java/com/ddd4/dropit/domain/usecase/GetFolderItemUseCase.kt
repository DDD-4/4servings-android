package com.ddd4.dropit.domain.usecase

import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.repository.DropitRepository

class GetFolderItemUseCase(
    private val dropitRepository: DropitRepository
){
    //suspend fun execute(): Result<List<DomainEntity.>>
}