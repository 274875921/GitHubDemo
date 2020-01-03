package com.zjx.githubapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import devlight.io.library.ntb.NavigationTabBar

/**
 *created by zjx
 * on 2019/12/26 0026
 */
class GitHubNavigationTabBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : NavigationTabBar(context,attrs,defStyleAttr)
{
    var isTouchAble = true


    var doubleClickListener:DoubleClickListener ?= null


    var gestureDetector = GestureDetector(context.applicationContext,object :GestureDetector.SimpleOnGestureListener(){
        override fun onDoubleTap(e: MotionEvent?): Boolean
        {
            doubleClickListener?.onDoubleClick(mIndex)
            return super.onDoubleTap(e)
        }
    })

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        if(!isTouchAble)
        {
            return true
        }
        super.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    interface DoubleClickListener
    {
        fun onDoubleClick(position: Int)
    }
}