package com.zjx.githubapp.common.net

import io.reactivex.ObservableSource
import io.reactivex.Observer
import retrofit2.Response

/**
 *created by zjx
 * on 2019/12/22 0022
 */

class FlatMapResponse2Result<T>(private val response: Response<T>) : ObservableSource<T>
{
    override fun subscribe(observer: Observer<in T?>)
    {
        if(response.isSuccessful)
        {
            observer.onNext(response.body())
        }
        else
        {
            observer.onError(Throwable(response.code().toString(), Throwable(response.errorBody().toString())))
        }
    }
}

class FlatMapResult2Response<T>(private val t:T):ObservableSource<Response<T>>
{
    override fun subscribe(observer: Observer<in Response<T>>)
    {
            observer.onNext(Response.success(t))
    }

}