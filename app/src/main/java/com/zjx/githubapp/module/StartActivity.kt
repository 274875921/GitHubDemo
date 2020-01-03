package com.zjx.githubapp.module

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zjx.githubapp.R
import org.jetbrains.anko.clearTask

/**
 *created by zjx
 * on 2019/12/19 0019
 */
class StartActivity :AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val intent = Intent(this,StartNavigationActivity::class.java)
        //清除栈内所有的Activity 让StartNavigationActivity成为栈顶元素
        intent.clearTask()
        finish()
    }
}