package com.ddd4.domain.repository


interface SharedPrefRepository: Repository {

    fun writePrefs(key: String?, value: Int?)

    fun writePrefs(key: String?, value: Long?)

    fun writePrefs(key: String?, value: String?)

    fun writePrefs(key: String?, value: Boolean)

    fun getPrefsBooleanValue(key: String?): Boolean

    fun getPrefsBooleanValue(key: String?, defaultValue: Boolean): Boolean

    fun resetPrefsIntValue(key: String?)

    fun getPrefsIntValue(key: String?): Int

    fun getPrefsIntValue(key: String?, defaultValue: Int?): Int

    fun getPrefsLongValue(key: String?): Long

    fun getPrefsStringValue(key: String?): String?

    fun removePrefsValue(key: String?)
}
