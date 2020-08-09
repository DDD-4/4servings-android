package com.ddd4.dropit.data.source.local

import com.ddd4.dropit.data.source.BaseDataSource
import com.ddd4.dropit.domain.entity.DomainEntity

interface LocalDataSource: BaseDataSource {

    suspend fun getFolders(): List<DomainEntity.Folder>

    suspend fun getItems(folderId: Long): List<DomainEntity.Item>
}