package com.zjx.githubapp.common

import android.content.Context
import android.content.SharedPreferences
import com.zjx.githubapp.GitHupApplication
import kotlin.reflect.KProperty

/**
 *created by zjx
 * on 2019/12/20 0020
 */
class GitHubPreference<T>(private val keyName:String,private val default:T)
{
    private val prefs: SharedPreferences by lazy {
        GitHupApplication.instance.getSharedPreferences(keyName, Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    {
        return getSharePreferences(keyName, default)
    }

    private fun getSharePreferences(keyName: String, default: T): T = with(prefs)
    {
        val res: Any = when (default) {
            is Long -> getLong(keyName, default)
            is String -> getString(keyName, default)
            is Int -> getInt(keyName, default)
            is Boolean -> getBoolean(keyName, default)
            is Float -> getFloat(keyName, default)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        return res as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
    {
        putSharePreferences(keyName, value)
    }

    private fun putSharePreferences(keyName: String, value: T) = with(prefs.edit())
    {
        when (value) {
            is Long -> putLong(keyName, value)
            is String -> putString(keyName, value)
            is Int -> putInt(keyName, value)
            is Boolean -> putBoolean(keyName, value)
            is Float -> putFloat(keyName, value)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }.apply()
    }
}