package com.ddd4.core.room

import androidx.room.*
import com.ddd4.model.entity.DropitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DropitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dropitEntity: DropitEntity)

    @Update
    suspend fun update(dropitEntity: DropitEntity)

    @Delete
    fun delete(dropitEntity: DropitEntity)

    @Query("SELECT * FROM drop_it_table ORDER BY d_day ASC")
    fun getAllData(): Flow<List<DropitEntity>>
}
