package com.zjx.githubapp.test

/**
 *created by zjx
 * on 2019/12/21 0021
 */
class Student
{
    lateinit var onClickDemoListener: OnClickDemoListener


    fun setOnClickLisetner(onClickDemoListener: OnClickDemoListener)
    {
        this.onClickDemoListener= onClickDemoListener
    }


}