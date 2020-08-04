package com.ddd4.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ddd4.domain.entity.DropitEntity

@Database(entities = [DropitEntity::class], version = 1, exportSchema = true)
abstract class AppDataBase: RoomDatabase(){

    abstract fun dropItDao(): DropitDao
}
