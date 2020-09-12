package com.ddd4.dropit.presentation.util

import java.util.*

fun intToDate(year: Int, month: Int, dayOfMonth: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar.time
}

fun addToDate(year: Int, month: Int, dayOfMonth: Int, addDay: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    calendar.add(Calendar.DATE, addDay)
    return calendar.time
}