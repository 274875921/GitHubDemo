package com.zjx.githubapp.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.zjx.githubapp.di.Injectable

/**
 *created by zjx
 * on 2019/12/20 0020
 */
abstract class BaseFragment<T:ViewDataBinding> :Fragment(),Injectable
{
    var dataBinding by AutoClearData<T>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),
            container,false,GitHubDataBindingComponent())

        return dataBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         createView(dataBinding?.root)
    }

    abstract fun createView(root: View?)

    abstract fun getLayoutId(): Int

    fun navigataionPopUpto(view:View,actionId:Int,args:Bundle?,isFinish:Boolean)
    {
        val controller = Navigation.findNavController(view)
        controller.navigate(actionId,args, NavOptions.Builder().setPopUpTo(controller.graph.id,true).build())
        if(isFinish)
        {
            activity?.finish()
        }
    }
}