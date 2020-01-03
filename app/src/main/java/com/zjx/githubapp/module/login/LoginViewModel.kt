package com.zjx.githubapp.module.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zjx.githubapp.R
import com.zjx.githubapp.common.GitHubPreference
import com.zjx.githubapp.common.config.AppConfig
import com.zjx.githubapp.repository.LoginRepository
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/21 0021
 */
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel()
{
    private var usernameStorage:String by GitHubPreference(AppConfig.USER_NAME,"")

    private var passwrodStroage:String by GitHubPreference(AppConfig.PASSWORD,"")

    val username = ObservableField<String>()

    val password = ObservableField<String>()

    val loginResult = MutableLiveData<Boolean>()

    fun onSubmitClick(view: View)
    {
        val useName = this.username.get()
        val password =  this.password.get()

        useName?.apply {
            if (this.isEmpty())
            {
                view.context.toast(R.string.LoginNameTip)
            }
        }
        password?.apply {
            if(this.isEmpty())
            {
                view.context.toast(R.string.LoginPWTip)
            }
        }
        login(view.context)

    }

    private fun login(context: Context?)
    {
        loginRepository.login(username.get()!!.trim(),password.get()!!.trim(),loginResult)
    }
}