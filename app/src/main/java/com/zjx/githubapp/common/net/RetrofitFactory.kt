package com.zjx.githubapp.common.net

import com.zjx.githubapp.BuildConfig
import com.zjx.githubapp.common.GitHubPreference
import com.zjx.githubapp.common.config.AppConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 *created by zjx
 * on 2019/12/21 0021
 */
class RetrofitFactory private constructor()
{
    private var accessTokenStorage:String by GitHubPreference(AppConfig.ACCESS_TOKEN,"")

    private var userBasicCodeStorage:String by GitHubPreference(AppConfig.USER_BASIC_CODE,"")

    val retrofit:Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = if(BuildConfig.DEBUG)
        {
            HttpLoggingInterceptor.Level.BODY
        }
        else
        {
            HttpLoggingInterceptor.Level.NONE
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(headerIntercptor())
            .addInterceptor(PageInfoInterceptor())
            .connectTimeout(AppConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.GITHUB_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    private fun headerIntercptor():Interceptor
    {
        return Interceptor {chain->
            var request = chain.request()
            val accessToken = getAuthorization()
            if(!accessToken.isEmpty())
            {
                val url = request.url().toString()
                request = request.newBuilder()
                    .addHeader("Authorization",accessToken)
                    .url(url).build()
            }
            chain.proceed(request)
        }
    }

    private fun getAuthorization(): String
    {
        if(accessTokenStorage.isBlank())
        {
            val basic =  userBasicCodeStorage
            return if(basic.isBlank())
            {
                ""
            }
            else
            {
                "Basic $basic"
            }
        }
        return "token $accessTokenStorage"
    }


    companion object
    {
        private var retrofitFactory:RetrofitFactory ?= null

        val instance:RetrofitFactory
        get() {
            if(retrofitFactory ==  null)
            {
                synchronized(RetrofitFactory::class.java)
                {
                    if(retrofitFactory == null)
                    {
                        retrofitFactory = RetrofitFactory()
                    }
                }
            }
            return retrofitFactory!!
        }

        fun <T> createService(claszz:Class<T>):T
        {
            return instance.retrofit.create(claszz)
        }


        fun <T> executeResult(observable:Observable<Response<T>>,resultObserver: ResultObserver<T>)
        {
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultObserver)
        }

    }
}