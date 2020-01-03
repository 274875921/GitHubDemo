package com.zjx.githubapp.module.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *created by zjx
 * on 2019/12/20 0020
 */

class AutoClearValue<T:Any?>(val fragment:Fragment) : ReadWriteProperty<Fragment,T?>
{
    var _value:T ?= null
    init {
        fragment.lifecycle.addObserver(object :LifecycleObserver{

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun clearValue()
            {
                _value = null
            }
        })
    }
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T?
    {
        return _value
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?)
    {
        _value = value
    }
}


fun <T:Any?> Fragment.AutoClearData() = AutoClearValue<T?>(this)