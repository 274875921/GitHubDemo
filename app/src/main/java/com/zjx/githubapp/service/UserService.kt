package com.zjx.githubapp.service

import com.zjx.githubapp.model.bean.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 *created by zjx
 * on 2019/12/25 0025
 */
interface UserService
{

    @GET
    fun getPersonInfo(@Header("forceNetWork") forceNetWork:Boolean):Observable<Response<User>>


    @GET("users/{user}")
    fun getUser(@Header("forceNetWork") forceNetWork : Boolean, @Path("user")userName: String): Observable<Response<User>>
}