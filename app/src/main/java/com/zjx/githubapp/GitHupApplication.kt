package com.zjx.githubapp

import android.app.Activity
import android.app.Application
import com.mikepenz.iconics.Iconics
import com.zjx.githubapp.common.db.RealmFactory
import com.zjx.githubapp.common.style.GSYIconfont
import com.zjx.githubapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 *created by zjx
 * on 2019/12/19 0019
 */
class GitHupApplication : Application(),HasActivityInjector
{
    companion object{
        var instance: GitHupApplication by Delegates.notNull()
    }

    @Inject
    lateinit var mActivityDispatcher:DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity>  = mActivityDispatcher


    override fun onCreate()
    {
        super.onCreate()
        instance = this
        AppInjector.init(this)

        Iconics.init(applicationContext)
        Iconics.registerFont(GSYIconfont)

        Realm.init(this)
        RealmFactory.instance

    }
}