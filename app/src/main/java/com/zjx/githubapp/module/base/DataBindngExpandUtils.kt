package com.zjx.githubapp.module.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent

/**
 *created by zjx
 * on 2019/12/20 0020
 */
class GitHubDataBindingComponent : DataBindingComponent{
    override fun getCompanion(): DataBindngExpandUtils.Companion {
        return DataBindngExpandUtils
    }
}

class DataBindngExpandUtils
{
    companion object{

        @BindingAdapter("image_blur")
        fun imageUrl(imageView:ImageView,url:String?)
        {

        }
    }
}