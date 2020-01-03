package com.zjx.githubapp.model.bean

import com.google.gson.annotations.SerializedName
import com.zjx.githubapp.BuildConfig
import com.zjx.githubapp.repository.LoginRepository
import java.util.*

/**
 *created by zjx
 * on 2019/12/22 0022
 */
class LoginRequestModel
{
    var scopes:List<String> ?= null
        private set
    var note:String ?= null
        private set
    @SerializedName("client_id")
    var clientId :String ?= null
        private set
    @SerializedName("client_secret")
    var clientSecret :String ?= null


    companion object{
        fun generate():LoginRequestModel
        {
            val model = LoginRequestModel()
            model.scopes = Arrays.asList("user","repo","gist","notifications")
            model.note = BuildConfig.APPLICATION_ID
            model.clientId = BuildConfig.CLIENT_ID
            model.clientSecret = BuildConfig.CLIENT_SECRET
            return model
        }
    }

}