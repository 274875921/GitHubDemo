package com.zjx.githubapp.model

import com.zjx.githubapp.model.ui.UserUiModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 *created by zjx
 * on 2019/12/20 0020
 */
@Singleton
class AppGlobalModel @Inject constructor()
{
    val userUiModel = UserUiModel()
}