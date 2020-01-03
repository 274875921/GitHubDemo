package com.zjx.githubapp.common.net

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.lang.NullPointerException
import java.lang.RuntimeException

/**
 *created by zjx
 * on 2019/12/20 0020
 */
object GsonUtils
{
    fun getNoteJsonString(jsonString:String,note:String):String
    {
        if(TextUtils.isEmpty(jsonString))
        {
            throw RuntimeException("getNoteJsonString jsonString empty")
        }
        if(TextUtils.isEmpty(note))
        {
            throw RuntimeException("getNoteJsonString note empty")
        }
        val element = JsonParser().parse(jsonString)
        if(element.isJsonNull)
        {
            throw RuntimeException("getNoteJsonString element empty")
        }
        return element.asJsonObject.get(note).toString()
    }

    fun <T> getListFromJsonString(jsonString:String,clazz:Class<T>):List<T>
    {
        if(TextUtils.isEmpty(jsonString))
        {
            throw RuntimeException("getListFromJsonString jsonString empty")
        }
        val element = JsonParser().parse(jsonString)
        if(element.isJsonNull)
        {
            throw RuntimeException("getListFromJsonString element empty")
        }
        if(element.isJsonObject)
        {
            throw RuntimeException("getListFromJsonString element is object")
        }
        val data = ArrayList<T>()

        for (jsonElement in element.asJsonArray)
        {
            val fromJson = Gson().fromJson(jsonElement, clazz) as T
            data.add(fromJson)
        }
        return data
    }

    fun <T> getObjectFromJsonString(jsonString:String,clazz:Class<T>):T
    {
        if(TextUtils.isEmpty(jsonString))
        {
            throw RuntimeException("getObjectFromJsonString jsonString empty")
        }
        val element = JsonParser().parse(jsonString)
        if(element.isJsonNull)
        {
            throw RuntimeException("getObjectFromJsonString element empty")
        }
        if(!element.isJsonObject)
        {
            throw RuntimeException("getObjectFromJsonString element is not Object")
        }
        return Gson().fromJson(element,clazz)
    }


    fun getStringFromObject(obj:Any?):String
    {
        return if(obj == null)
        {
            throw NullPointerException("getStringFromObject obj = null")
        }
        else
        {
            Gson().toJson(obj)
        }
    }
}