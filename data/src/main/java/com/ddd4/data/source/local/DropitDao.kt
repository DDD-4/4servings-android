package com.ddd4.data.source.local

import androidx.room.*
import com.ddd4.data.entity.DropitDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DropitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dropitDataModel: DropitDataModel)

    @Update
    suspend fun update(dropitDataModel: DropitDataModel)

    @Delete
    fun delete(dropitDataModel: DropitDataModel)

    @Query("SELECT * FROM drop_it_table ORDER BY d_day ASC")
    fun getAllData(): Flow<List<DropitDataModel>>
}
