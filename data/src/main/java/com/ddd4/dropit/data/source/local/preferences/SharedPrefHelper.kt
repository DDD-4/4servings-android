package com.ddd4.dropit.data.source.local.preferences

import android.content.Context

class SharedPrefHelper(context: Context) {

    /*
    @Volatile
    private var instance: SharedPrefHelper? = null
    private var mPrefs: SharedPreferences? = null
    private var mPrefsEditor: SharedPreferences.Editor? = null

    private fun SharedPrefHelper(context: Context) {
        mPrefs = context.getSharedPreferences(dropIt, 0)
    }

    // SharedPreferenceHelper is a singleton pattern
    fun getInstance(context: Context): SharedPrefHelper? {
        if (instance == null) {
            synchronized(SharedPrefHelper::class.java) {
                if (instance == null) {
                    instance = SharedPrefHelper(context)
                }
            }
        }
        return instance
    }

    fun writePrefs(key: String?, value: Int?) {
        if (value == null) {
            return
        }
        mPrefsEditor = mPrefs!!.edit()
        mPrefsEditor.putInt(key, value).apply()
    }

    fun writePrefs(key: String?, value: Long?) {
        if (value == null) {
            return
        }
        mPrefsEditor = mPrefs!!.edit()
        mPrefsEditor.putLong(key, value).apply()
    }

    fun writePrefs(key: String?, value: String?) {
        mPrefsEditor = mPrefs!!.edit()
        mPrefsEditor.putString(key, value).apply()
    }

    fun writePrefs(key: String?, value: Boolean) {
        mPrefsEditor = mPrefs!!.edit()
        mPrefsEditor.putBoolean(key, value).apply()
    }

    fun getPrefsBooleanValue(key: String?): Boolean {
        return mPrefs!!.getBoolean(key, false)
    }

    fun getPrefsBooleanValue(
        key: String?,
        defaultValue: Boolean
    ): Boolean {
        return mPrefs!!.getBoolean(key, defaultValue)
    }

    fun resetPrefsIntValue(key: String?) {
        writePrefs(key, 0)
    }

    fun getPrefsIntValue(key: String?): Int {
        return mPrefs!!.getInt(key, 0)
    }

    fun getPrefsIntValue(key: String?, defaultValue: Int?): Int {
        return mPrefs!!.getInt(key, defaultValue!!)
    }

    fun getPrefsLongValue(key: String?): Long {
        return mPrefs!!.getLong(key, 0L)
    }

    fun getPrefsStringValue(key: String?): String? {
        return mPrefs!!.getString(key, "")
    }

    fun removePrefsValue(key: String?) {
        if (mPrefs!!.contains(key)) {
            mPrefsEditor = mPrefs!!.edit()
            mPrefsEditor.remove(key).apply()
        }
    }*/
}