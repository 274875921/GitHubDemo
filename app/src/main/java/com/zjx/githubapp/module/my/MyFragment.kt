package com.zjx.githubapp.module.my


import android.view.View
import androidx.fragment.app.Fragment
import com.zjx.githubapp.R
import com.zjx.githubapp.databinding.FragmentMyBinding
import com.zjx.githubapp.module.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class MyFragment : BaseFragment<FragmentMyBinding>()
{

    override fun createView(root: View?)
    {

    }

    override fun getLayoutId(): Int = R.layout.fragment_my

}
