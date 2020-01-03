package com.zjx.githubapp.repository

import com.zjx.githubapp.GitHupApplication
import com.zjx.githubapp.common.GitHubPreference
import com.zjx.githubapp.common.config.AppConfig
import com.zjx.githubapp.common.net.FlatMapResponse2Result
import com.zjx.githubapp.common.net.GsonUtils
import com.zjx.githubapp.common.net.PageInfo
import com.zjx.githubapp.model.AppGlobalModel
import com.zjx.githubapp.model.bean.User
import com.zjx.githubapp.model.ui.UserConversion
import com.zjx.githubapp.repository.dao.UserDao
import com.zjx.githubapp.service.RepoService
import com.zjx.githubapp.service.UserService
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *created by zjx
 * on 2019/12/22 0022
 */
class UserRepository @Inject constructor(private val retrofit: Retrofit,private val appGlobalModel: AppGlobalModel,
                                         private val application: GitHupApplication,private val userDao: UserDao)
{

    private var userInfoStorage:String by GitHubPreference(AppConfig.USER_INFO,"")

    fun getPersonInfoObservable(userName:String?= null):Observable<User>
    {
        val isLoginUser = userName == null
        val userService  = if(isLoginUser)
        {
            retrofit.create(UserService::class.java).getPersonInfo(true)
        }
        else
        {
            retrofit.create(UserService::class.java).getUser(true,userName!!)
        }
        return doUserInfoFlat(userService,isLoginUser)
    }

    private fun doUserInfoFlat(userService: Observable<Response<User>>, loginUser: Boolean): Observable<User>
    {
        return userService.flatMap {
            FlatMapResponse2Result(it)
        }.flatMap {
            val startedService = retrofit.create(RepoService::class.java).getStarredRepos(loginUser,it.name!!,1,"updated",1)
            val honorService = retrofit.create(RepoService::class.java).getUserRepository100StatusDao(loginUser,it.name!!,1)
            val starResponse= startedService.blockingSingle()
            val honnorResponse = honorService.blockingSingle()
            val starPageString = starResponse.headers().get("page_info")
            if (starPageString != null) {
                val pageInfo = GsonUtils.getObjectFromJsonString(starPageString, PageInfo::class.java)
                it.starRepos = if (pageInfo.last < 0) {
                    0
                } else {
                    pageInfo.last
                }
            }
            if (honnorResponse.isSuccessful) {
                val list = honnorResponse.body()
                var count = 0
                list?.forEach {
                    count += it.watchersCount
                }
                it.honorRepos = count
            }
            Observable.just(it)
        }.doOnNext {
            if(loginUser)
            {
                userInfoStorage = GsonUtils.getStringFromObject(it)
                UserConversion.cloneDataFromUser(application,it,appGlobalModel.userUiModel)
            }
        }.onErrorResumeNext {t:Throwable->
            Observable.error(t)
        }

    }
}