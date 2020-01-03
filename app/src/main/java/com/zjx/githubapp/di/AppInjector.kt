package com.zjx.githubapp.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.zjx.githubapp.GitHupApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 *created by zjx
 * on 2019/12/19 0019
 */
object AppInjector
{
    fun init(gitHupApplication: GitHupApplication)
    {
        DaggerAppDi.builder()
            .application(gitHupApplication)
            .build()
            .inject(gitHupApplication)
        gitHupApplication.registerActivityLifecycleCallbacks(object:Application.ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle)
            {
                handleAcitvity(activity)
            }

        })
    }

    private fun handleAcitvity(activity: Activity)
    {
        if(activity is HasSupportFragmentInjector)
        {
            AndroidInjection.inject(activity)
        }
        //TODO Activity路由注册
        if(activity is FragmentActivity)
        {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object :FragmentManager.FragmentLifecycleCallbacks(){
                override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
                {
                    if(f is Injectable)
                    {
                        AndroidSupportInjection.inject(f)
                    }
                    //TODO Fragment路由注册
                }
            },true)
        }
    }
}