package com.ddd4.dropit.data.source.local.room

import androidx.room.*
import com.ddd4.dropit.data.entity.DataEntity
import com.ddd4.dropit.data.source.local.model.Category
import com.ddd4.dropit.domain.entity.DomainEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folderData: DataEntity.Folder)

    //@Update
    //suspend fun updateFolder(folderData: DataEntity.Folder)

    //@Delete
    //suspend fun deleteFolder(folderData: DataEntity.Folder)

    @Query("SELECT * FROM folder")
    suspend fun selectFolders(): List<DataEntity.Folder>

    @Query("SELECT * FROM folder WHERE name = :folderName")
    suspend fun selectFolderByName(folderName: String): DataEntity.Folder

    @Query("SELECT * FROM folder WHERE id = :folderId")
    suspend fun selectFolderById(folderId: Long): DataEntity.Folder

    @Query("SELECT * FROM category WHERE id = :categoryId")
    suspend fun selectCategoryById(categoryId: Long): DataEntity.Category

    @Insert
    suspend fun insertItem(item: DataEntity.Item): Long

    @Update
    suspend fun updateItem(item: DataEntity.Item)

    @Query("UPDATE item SET folder_id = :folderId WHERE id = :itemId")
    suspend fun updateItemByFolderId(folderId: Long, itemId: Long)

    //@Update
    //suspend fun updateItem(folderData: DataEntity.Item)

    //@Delete
    //suspend fun deleteItem(folderData: DataEntity.Item)

    @Query("DELETE FROM item WHERE id = :itemId")
    suspend fun deleteItem(itemId: Long)

    @Query("SELECT * FROM item WHERE folder_id = :folderId")
    suspend fun selectItemsByFolder(folderId: Long): List<DataEntity.Item>

    @Query("SELECT * FROM item WHERE category_id = :categoryId")
    suspend fun selectItemsByCategory(categoryId: Long): List<DataEntity.Item>

    @Query("SELECT * FROM item WHERE id = :itemId")
    suspend fun selectItem(itemId: Long): DataEntity.Item

    @Transaction
    suspend fun insertSection(sections: List<Category>) {

        val categories = ArrayList<DataEntity.Category>()
        val subCategories = ArrayList<DataEntity.SubCategory>()

        for (i in sections.indices) {
            categories.add(DataEntity.Category(id = sections[i].id, title = sections[i].title))

            for (j in sections[i].subCategory.indices) {
                subCategories.add(DataEntity.SubCategory(
                    id = 0,
                    categoryId = sections[i].id,
                    endAt = sections[i].subCategory[j].endAt,
                    title = sections[i].subCategory[j].title))
            }
        }

        insertCategories(categories)
        insertSubCategories(subCategories)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<DataEntity.Category>)

    @Query("SELECT * FROM category ORDER BY id ASC")
    suspend fun selectCategories(): List<DataEntity.Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategories(categories: List<DataEntity.SubCategory>)

    @Query("SELECT * FROM subcategory WHERE category_id = :categoryId ORDER BY id ASC")
    suspend fun selectSubCategories(categoryId: Long): List<DataEntity.SubCategory>

    @Query("SELECT * FROM item")
    suspend fun selectItemAlarmIds(): List<DataEntity.Item>

}
