package com.zjx.githubapp.di

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.mikepenz.iconics.IconicsColor
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.utils.sizeDp
import com.zjx.githubapp.GitHupApplication
import com.zjx.githubapp.R
import com.zjx.githubapp.common.style.GSYIconfont
import com.zjx.githubapp.module.dynamic.DynamicFragment
import com.zjx.githubapp.module.my.MyFragment
import com.zjx.githubapp.module.trend.TrendFragment
import dagger.Module
import dagger.Provides
import devlight.io.library.ntb.NavigationTabBar

/**
 *created by zjx
 * on 2019/12/26 0026
 */
@Module
class MainActivityModule
{
    @Provides
    fun providFragmetns():List<Fragment>
    {
        return listOf(MyFragment(),TrendFragment(),DynamicFragment())
    }

    @Provides
    fun provideNavigationModel(application: GitHupApplication):List<NavigationTabBar.Model>
    {
        return listOf(NavigationTabBar.Model.Builder(
            IconicsDrawable(application)
                .icon(GSYIconfont.Icon.GSY_MAIN_DT)
                .color(IconicsColor.colorInt(R.color.subTextColor))
                .sizeDp(20), Color.parseColor("#00000000"))
            .title(application.getString(R.string.tabDynamic))
            .build(),

            NavigationTabBar.Model.Builder(
                IconicsDrawable(application)
                    .icon(GSYIconfont.Icon.GSY_MAIN_QS)
                    .color(IconicsColor.colorInt(R.color.subTextColor))
                    .sizeDp(20), Color.parseColor("#00000000"))
                .title(application.getString(R.string.tabRecommended))
                .build(),

            NavigationTabBar.Model.Builder(
                IconicsDrawable(application)
                    .icon(GSYIconfont.Icon.GSY_MAIN_MY)
                    .color(IconicsColor.colorInt(R.color.subTextColor))
                    .sizeDp(20), Color.parseColor("#00000000"))
                .title(application.getString(R.string.tabMy))
                .build())
    }

}