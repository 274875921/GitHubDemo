package com.zjx.githubapp.di

import com.zjx.githubapp.di.annotation.ActivityScope
import com.zjx.githubapp.module.StartNavigationActivity
import com.zjx.githubapp.module.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *created by zjxon 2019/12/19 0019
 *
 *
@Module(subcomponents = ActivityBindModule_InjectStartActivity.StartActivitySubcomponent.class)
public abstract class ActivityBindModule_InjectStartActivity
{
    private ActivityBindModule_InjectStartActivity()
    {

    }

    @Binds
    @IntoMap
    @ClassKey(StartActivity.class)
    abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(StartActivitySubcomponent.Factory builder);

    @Subcomponent
    public interface StartActivitySubcomponent extends AndroidInjector<StartActivity>
    {
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<StartActivity>
        {

        }
    }
}

@Module
abstract class ActivityBindModule
{
        /**
         * 这里用@ContributesAndroidInjector注解的作用解释:
         * 1.生成了 一个带有@Subcomponent 注解的StartActivitySubComponent接口，并且该接口继承了AndroidInjector<StartActivity>
         *   作为一个Subcomponent 它需要有2点要被实现
         *     A.要内部需要实现有一个 接口被标注了@Subcomponent.Builder 然后提供 StartActivitySubComponent build()方法
         *      而通过@ContributesAndroidInjector @Subcomponent.Builder 会被修改成 @Subcomponent.Factory
         *                                       StartActivitySubComponent build() 会被修改成 AndroidInject create(StartActivity activity)
         *    B Module抽象类需要标注SubComponet的值
        */
        @ContributesAndroidInjector
        abstract fun injectStartActivity():StartActivity
}

 */
@Module
abstract class ActivityBindModule
{
    @ActivityScope
    @ContributesAndroidInjector(modules = [StartFragmentModule::class])
    abstract fun injectStartNavigationActivity(): StartNavigationActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class,MainActivityModule::class])
    abstract fun injectMainActivity():MainActivity
}