package com.ddd4.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drop_it_table")
data class DropitEntity(
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name = "product") var product: String,
    @ColumnInfo(name = "expiration_date") var expirationDate: String,
    @ColumnInfo(name = "d_day") var dday: Long
)
