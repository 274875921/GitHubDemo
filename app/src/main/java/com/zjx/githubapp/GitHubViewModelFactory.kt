package com.zjx.githubapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 *created by zjx
 * on 2019/12/21 0021
 */
@Singleton
class GitHubViewModelFactory @Inject constructor(private val createors:Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        val creator = createors[modelClass] ?: createors.entries.firstOrNull{
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        }catch (e:Exception)
        {
            throw RuntimeException(e)
        }
    }
}