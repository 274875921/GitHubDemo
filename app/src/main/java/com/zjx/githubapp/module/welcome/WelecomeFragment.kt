package com.zjx.githubapp.module.welcome

import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zjx.githubapp.R
import com.zjx.githubapp.common.GitHubPreference
import com.zjx.githubapp.common.net.GsonUtils
import com.zjx.githubapp.common.config.AppConfig
import com.zjx.githubapp.databinding.FragmentWeclcomeBinding
import com.zjx.githubapp.model.AppGlobalModel
import com.zjx.githubapp.model.bean.User
import com.zjx.githubapp.model.ui.UserConversion
import com.zjx.githubapp.module.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weclcome.*
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/19 0019
 */
class WelecomeFragment:BaseFragment<FragmentWeclcomeBinding>()
{
    private val userInfoStore:String by GitHubPreference(AppConfig.USER_INFO,"")

    private val accessTokenStore:String by GitHubPreference(AppConfig.ACCESS_TOKEN,"")

    @Inject
    lateinit var appGlobalModel: AppGlobalModel

    override fun createView(root: View?)
    {
        welcome_animation.speed = 5.0f

        Handler().postDelayed({
            goNext(view!!)
        },200)

    }
    override fun getLayoutId(): Int = R.layout.fragment_weclcome



    private fun goNext(view: View)
    {
        if(accessTokenStore.isEmpty())
        {
            navigataionPopUpto(view,R.id.action_nav_wel_to_login,null,false)
        }
        else
        {
            if(userInfoStore.isEmpty())
            {
                navigataionPopUpto(view,R.id.action_nav_wel_to_login,null,false)
            }
            else
            {
                UserConversion.cloneDataFromUser(activity,
                    GsonUtils.getObjectFromJsonString(userInfoStore,User::class.java),appGlobalModel.userUiModel)
                navigataionPopUpto(view,R.id.action_nav_wel_to_main,null,true)
            }
        }


    }

}