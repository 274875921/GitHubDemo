package com.zjx.githubapp.module.main

import org.jetbrains.anko.toast

/**
 *created by zjx
 * on 2019/12/28 0028
 */
class MainExitLogic(val activity: MainActivity)
{
    private var time :Long = 0L
    fun pressedBack()
    {
        val currentTime = System.currentTimeMillis()
        if(currentTime - time >  2000L)
        {
            activity.toast("再按一次退出")
            time = currentTime
        }
        else
        {
            System.exit(0)
        }

    }
}