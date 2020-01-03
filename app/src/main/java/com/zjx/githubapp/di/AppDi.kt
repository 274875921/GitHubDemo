package com.zjx.githubapp.di

import com.zjx.githubapp.GitHupApplication
import com.zjx.githubapp.common.db.RealmFactory
import com.zjx.githubapp.common.net.RetrofitFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *created by zjx
 * on 2019/12/19 0019
 */

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBindModule::class])
interface AppDi {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(gitHupApplication: GitHupApplication):Builder

        fun build():AppDi
    }

    fun inject(gitHupApplication: GitHupApplication)
}


@Module(includes = [ViewModelModule::class])
class AppModule
{
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {
        return RetrofitFactory.instance.retrofit
    }

    @Provides
    @Singleton
    fun provideRealmFactory():RealmFactory
    {
        return RealmFactory.instance
    }

}