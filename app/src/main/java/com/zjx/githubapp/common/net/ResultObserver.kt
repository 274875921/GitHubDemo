package com.zjx.githubapp.common.net

import android.accounts.NetworkErrorException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 *created by zjx
 * on 2019/12/21 0021
 */
abstract class ResultObserver<T> :Observer<Response<T>>
{
    override fun onComplete()
    {

    }
    override fun onSubscribe(d: Disposable)
    {
        if(d.isDisposed)
        {
            onRequestStart()
        }
    }

    override fun onNext(value: Response<T>)
    {
        onPageInfo(value)
        onRequestEnd()
        if(value.isSuccessful)
        {
            try {
                onSuccess(value.body())
            }catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
        else
        {
            try {
                onCodeError(value.code(),value.message())
            }catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
    }


    private fun onPageInfo(value: Response<T>)
    {
        val pageInfo = value.headers()["page_info"]
        pageInfo?.run {
            val pageInfo = GsonUtils.getObjectFromJsonString(pageInfo,PageInfo::class.java)
            onPageInfo(pageInfo.first,pageInfo.next-1,pageInfo.last)
        }
    }

    override fun onError(e: Throwable)
    {
        onRequestEnd()
        try {
            if(e is ConnectException || e is TimeoutException
                || e is NetworkErrorException
                || e is UnknownHostException)
            {
                onFailure(e,true)
            }
            else
            {
                onFailure(e,false)
            }

        }catch (e1:Exception)
        {
            e1.printStackTrace()
        }
    }

    @Throws(java.lang.Exception::class)
    abstract fun onCodeError(code: Int, message: String)

    @Throws(java.lang.Exception::class)
    abstract fun onSuccess(body: T?)

    @Throws(java.lang.Exception::class)
    abstract fun onFailure(e: Throwable, b: Boolean)


    open fun onRequestStart()
    {
    }
    open fun onPageInfo(fisrt: Int, current: Int, last: Int)
    {
    }
    open fun onRequestEnd()
    {
    }

}