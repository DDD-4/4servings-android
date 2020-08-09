package com.ddd4.dropit.domain.repository

import com.ddd4.dropit.domain.entity.DomainEntity

interface DropitRepository {

    suspend fun getFolders(): List<DomainEntity.Folder>

    suspend fun getItems(folderId: Long): List<DomainEntity.Item>
}
