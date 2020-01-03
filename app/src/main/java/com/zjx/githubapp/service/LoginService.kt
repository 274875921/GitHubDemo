package com.zjx.githubapp.service

import com.zjx.githubapp.model.bean.AccessToken
import com.zjx.githubapp.model.bean.LoginRequestModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 *created by zjx
 * on 2019/12/22 0022
 */
interface LoginService
{
    @POST("authorizations")
    @Headers("Accept:application/json")
    fun authorization(@Body loginRequestModel: LoginRequestModel):Observable<Response<AccessToken>>

}