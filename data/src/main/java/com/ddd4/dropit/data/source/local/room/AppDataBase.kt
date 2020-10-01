package com.ddd4.dropit.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ddd4.dropit.data.entity.DataEntity
import java.util.*

@Database(entities = [
    DataEntity.Folder::class, DataEntity.Item::class,
    DataEntity.Category::class, DataEntity.SubCategory::class]
    , version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class AppDataBase: RoomDatabase(){
    abstract fun databaseDao(): DatabaseDao
}

class DateConverter {
    @TypeConverter fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }
    @TypeConverter fun dateToTimestamp(date: Date?): Long? = date?.time
}
