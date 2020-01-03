package com.zjx.githubapp.repository

import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.zjx.githubapp.common.GitHubPreference
import com.zjx.githubapp.common.config.AppConfig
import com.zjx.githubapp.common.net.FlatMapResponse2Result
import com.zjx.githubapp.common.net.FlatMapResult2Response
import com.zjx.githubapp.common.net.ResultObserver
import com.zjx.githubapp.common.net.RetrofitFactory
import com.zjx.githubapp.model.bean.LoginRequestModel
import com.zjx.githubapp.model.bean.User
import com.zjx.githubapp.service.LoginService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/22 0022
 */

class LoginRepository @Inject constructor(private val retrofit: Retrofit,private val userRepository: UserRepository)
{

    private var userNameStorage:String by GitHubPreference(AppConfig.USER_NAME,"")

    private var passwordStroage:String by GitHubPreference(AppConfig.PASSWORD,"")

    private var accessTokenStorage:String by GitHubPreference(AppConfig.ACCESS_TOKEN,"")

    private var userBasicCodeStroage:String by GitHubPreference(AppConfig.USER_BASIC_CODE,"")

    private var userInfoStorage:String by GitHubPreference(AppConfig.USER_INFO,"")


    fun login(userName: String, passwrod: String, loginResult: MutableLiveData<Boolean>)
    {
        clearTokenStroage()
        val type = "$userName:$passwrod"
        val base64 = Base64.encodeToString(type.toByteArray(),Base64.NO_WRAP).replace("\\+","%2B")
        userNameStorage = userName
        userBasicCodeStroage = base64
        val loginService = getTokenObservable()
        val userService = userRepository.getPersonInfoObservable()
//        val authoizations = Observable.zip(loginService,userName)
        val authoizations = Observable
            .zip(loginService,userService, BiFunction <String,User,User>{ t1, t2 -> t2 })
            .flatMap {
                FlatMapResult2Response(it)
            }
        RetrofitFactory.executeResult(authoizations,object :ResultObserver<User>()
        {
            override fun onCodeError(code: Int, message: String) {
                loginResult.value = false
            }

            override fun onSuccess(body: User?) {
                passwordStroage = passwrod
                loginResult.value = true
            }

            override fun onFailure(e: Throwable, b: Boolean)
            {
                loginResult.value = false
            }

        })
    }

    private fun getTokenObservable(): Observable<String>
    {
        return retrofit.create(LoginService::class.java)
            .authorization(LoginRequestModel.generate())
            .flatMap {
                FlatMapResponse2Result(it)
            }
            .map {
                it.token ?: ""
            }.doOnNext {
                accessTokenStorage = it
            }.onErrorResumeNext { t : Throwable ->
                clearTokenStroage()
                Observable.error(t)
            }
    }


    private fun clearTokenStroage()
    {
        accessTokenStorage = ""
        userBasicCodeStroage = ""
    }

}