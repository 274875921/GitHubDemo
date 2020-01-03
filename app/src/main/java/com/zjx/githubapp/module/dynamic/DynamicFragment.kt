package com.zjx.githubapp.module.dynamic
import android.view.View
import androidx.fragment.app.Fragment
import com.zjx.githubapp.R
import com.zjx.githubapp.databinding.FragmentDynamicBinding
import com.zjx.githubapp.module.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class DynamicFragment : BaseFragment<FragmentDynamicBinding>()
{
    override fun createView(root: View?) {
    }

    override fun getLayoutId(): Int  = R.layout.fragment_dynamic


    fun reflashData()
    {

    }

}
