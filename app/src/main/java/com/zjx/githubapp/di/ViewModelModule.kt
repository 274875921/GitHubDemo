package com.zjx.githubapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zjx.githubapp.GitHubViewModelFactory
import com.zjx.githubapp.di.annotation.ViewModelKey
import com.zjx.githubapp.module.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *created by zjx
 * on 2019/12/21 0021
 */
@Module
abstract  class ViewModelModule
{
    @Binds
    abstract fun provideModelFactory(factory:GitHubViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel):ViewModel
}