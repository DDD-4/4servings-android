package com.ddd4.data.source.local

import androidx.room.*
import com.ddd4.data.entity.DropitDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DropitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dropitDataEntity: DropitDataEntity)

    @Update
    suspend fun update(dropitDataEntity: DropitDataEntity)

    @Delete
    fun delete(dropitDataEntity: DropitDataEntity)

    @Query("SELECT * FROM drop_it_table ORDER BY d_day ASC")
    fun getAllData(): Flow<List<DropitDataEntity>>
}
