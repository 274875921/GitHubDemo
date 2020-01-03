package com.zjx.githubapp.di

import com.zjx.githubapp.module.dynamic.DynamicFragment
import com.zjx.githubapp.module.login.LoginFragment
import com.zjx.githubapp.module.my.MyFragment
import com.zjx.githubapp.module.trend.TrendFragment
import com.zjx.githubapp.module.welcome.WelecomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *created by zjx
 * on 2019/12/19 0019
 */
@Module
abstract class StartFragmentModule
{
    @ContributesAndroidInjector
    abstract fun injectWlecomFragment():WelecomeFragment

    @ContributesAndroidInjector
    abstract fun injectLoginFragment():LoginFragment
}

@Module
abstract class MainFragmentModule
{
    @ContributesAndroidInjector
    abstract fun injectMyFragment():MyFragment

    @ContributesAndroidInjector
    abstract fun injectTrendFragment():TrendFragment

    @ContributesAndroidInjector
    abstract fun injectDynamicFragment():DynamicFragment
}