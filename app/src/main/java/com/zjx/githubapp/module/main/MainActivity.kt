package com.zjx.githubapp.module.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.LayoutInflaterCompat
import androidx.fragment.app.Fragment
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import com.zjx.githubapp.R
import com.zjx.githubapp.model.AppGlobalModel
import com.zjx.githubapp.module.dynamic.DynamicFragment
import com.zjx.githubapp.repository.IssueRepository
import com.zjx.githubapp.repository.LoginRepository
import com.zjx.githubapp.repository.ReposRepository
import com.zjx.githubapp.ui.adapter.FragmentPageViewAdaper
import com.zjx.githubapp.ui.view.GitHubNavigationTabBar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),HasSupportFragmentInjector, Toolbar.OnMenuItemClickListener {

    @Inject
    lateinit var globalModel: AppGlobalModel

    @Inject
    lateinit var mDispatchAndroidInject:DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mainFragmentList:MutableList<Fragment>

    @Inject
    lateinit var mainTabModel:MutableList<NavigationTabBar.Model>

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var issueRepository: IssueRepository

    @Inject
    lateinit var repository: ReposRepository

    val pressed = MainExitLogic(this)

    override fun onBackPressed()
    {
        pressed.pressedBack()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mDispatchAndroidInject

    override fun onCreate(savedInstanceState: Bundle?)
    {
        LayoutInflaterCompat.setFactory2(layoutInflater,IconicsLayoutInflater2(delegate))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()

        initToolBar()

        MainDrawerController(this,home_tool_bar,loginRepository,issueRepository,repository,globalModel)

    }

    private fun initToolBar()
    {
        setSupportActionBar(home_tool_bar)
        val action_bar = supportActionBar
        action_bar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        home_tool_bar.setTitle(R.string.app_name)
        home_tool_bar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean
    {
        when(item?.itemId)
        {
            R.id.action_search->{

            }
        }
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_toolbar_menu,menu)
        return true
    }

    private fun initViewPager()
    {
        home_view_pager.adapter = FragmentPageViewAdaper(mainFragmentList,supportFragmentManager)
        home_navigation_tab_bar.models = mainTabModel
        home_navigation_tab_bar.setViewPager(home_view_pager,0)
        home_view_pager.offscreenPageLimit = mainFragmentList.size
        home_navigation_tab_bar.doubleClickListener = object : GitHubNavigationTabBar.DoubleClickListener{
            override fun onDoubleClick(position: Int)
            {
                if(position == 0)
                {
                    val daymicFragment = mainFragmentList[position] as DynamicFragment
                    daymicFragment.reflashData()

                }
            }

        }
    }
}
