package com.ddd4.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ddd4.data.entity.DropitDataEntity

@Database(entities = [DropitDataEntity::class], version = 1, exportSchema = true)
abstract class AppDataBase: RoomDatabase(){

    abstract fun dropItDao(): DropitDao
}
