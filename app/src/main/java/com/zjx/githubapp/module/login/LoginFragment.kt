package com.zjx.githubapp.module.login

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zjx.githubapp.R
import com.zjx.githubapp.databinding.FragmentLoginBinding
import com.zjx.githubapp.module.base.BaseFragment
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/19 0019
 */
class LoginFragment :BaseFragment<FragmentLoginBinding>()
{
    @Inject
    lateinit var mFactory:ViewModelProvider.Factory

    lateinit var mLoginViewModel: LoginViewModel

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun createView(root: View?)
    {
        mLoginViewModel = ViewModelProviders.of(this,mFactory).get(LoginViewModel::class.java)

        dataBinding?.loginViewModel = mLoginViewModel

        mLoginViewModel.loginResult.observe(this, Observer{
            //根据结果返回，跳转主页
            if (it != null && it == true) {
                navigataionPopUpto(root!!,R.id.action_nav_login_to_main,null , true)
            } else {
                activity?.toast(R.string.LoginFailTip)
            }
        })

    }
}