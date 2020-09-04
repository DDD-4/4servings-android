package com.ddd4.dropit.presentation.entity

import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.floor

sealed class PresentationEntity {

    data class Folder (
        var id: Long,
        var name: String,
        var createAt: Date,
        var updateAt: Date? = Date()
    ): PresentationEntity()

    data class Item (
        var id: Long? = 0,
        var folderId: Long? = 0,
        var categoryId: Long,
        var subCategoryId: Long,
        var alarmId: Long,
        var name: String,
        var image: String? = null,
        var startAt: Date,
        var endAt: Date,
        var createAt: Date?,
        var updateAt: Date? = Date()
    ): PresentationEntity() {
        fun getDDay(): String {
            val day = (endAt.time - startAt.time) / TODAY
            Timber.e("day :$day AND start: $startAt ${startAt.time}, end: $endAt ${endAt.time}")
            return "D-$day"
        }

        fun getStartDate(): String {
            val formatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
            return formatter.format(startAt)
        }

        fun getEndDate(): String {
            val formatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
            return formatter.format(endAt)
        }
    }


    data class Category (
        var id: Long,
        var title: String
    ): PresentationEntity()

    data class SubCategory(
        val id: Long,
        var endAt: Int,
        val title: String
    ): PresentationEntity()
}

const val TODAY = 24 * 60 * 60 * 1000