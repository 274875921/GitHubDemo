package com.zjx.githubapp.module
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.zjx.githubapp.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/19 0019
 */
class StartNavigationActivity:AppCompatActivity(),HasSupportFragmentInjector
{
    @Inject
    lateinit var mDispatcherFragmentInjection:DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mDispatcherFragmentInjection

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_navigation)
    }

    override fun onBackPressed()
    {
        val rootFragment = supportFragmentManager.primaryNavigationFragment
        if(rootFragment is NavHostFragment)
        {
            if(rootFragment.navController.currentDestination?.id == R.id.start_login)
            {
                super.onBackPressed()
            }
        }


    }

}