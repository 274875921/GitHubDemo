package com.zjx.githubapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *created by zjx
 * on 2019/12/28 0028
 */
class FragmentPageViewAdaper(private val fragmentList:List<Fragment>, fm :FragmentManager) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int  = fragmentList.size

}