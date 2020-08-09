package com.ddd4.dropit.data.source.local.room

import androidx.room.*
import com.ddd4.dropit.data.entity.DataEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folderData: DataEntity.Folder)

    @Delete
    suspend fun updateFolder(folderData: DataEntity.Folder)

    @Delete
    suspend fun deleteFolder(folderData: DataEntity.Folder)

    @Query("SELECT * FROM folder")
    suspend fun selectFolders(): List<DataEntity.Folder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(folderData: DataEntity.Item)

    @Delete
    suspend fun updateItem(folderData: DataEntity.Item)

    @Delete
    suspend fun deleteItem(folderData: DataEntity.Item)

    @Query("SELECT * FROM item WHERE folder_id = :folderId")
    suspend fun selectItems(folderId: Long): List<DataEntity.Item>
}
