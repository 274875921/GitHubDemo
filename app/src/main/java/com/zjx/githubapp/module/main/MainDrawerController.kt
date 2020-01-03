package com.zjx.githubapp.module.main

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.zjx.githubapp.R
import com.zjx.githubapp.model.AppGlobalModel
import com.zjx.githubapp.repository.IssueRepository
import com.zjx.githubapp.repository.LoginRepository
import com.zjx.githubapp.repository.ReposRepository

class MainDrawerController(
   private val  mainActivity: MainActivity,
   private val  home_tool_bar: Toolbar,
   private val loginRepository: LoginRepository,
   private val issueRepository: IssueRepository,
   private val repository: ReposRepository,
   private val globalModel: AppGlobalModel)
{
    var drawer: Drawer ?= null

   init{
      drawer = DrawerBuilder()
         .withActivity(mainActivity)
         .withToolbar(home_tool_bar)
         .withSelectedItem(-1)
         .addDrawerItems(
            PrimaryDrawerItem()
               .withName(R.string.feedback)
               .withTextColor(R.color.colorPrimary)
               .withOnDrawerItemClickListener(object:Drawer.OnDrawerItemClickListener{
                  override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean
                  {
                     feedBack()
                     unSelectd(drawerItem)
                     return true
                  }
               }),
            PrimaryDrawerItem()
               .withName(R.string.person)
               .withTextColor(R.color.colorPrimary)
               .withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                  override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean
                  {
                     unSelectd(drawerItem)
                     return true
                  }
               }),
            PrimaryDrawerItem()
               .withName(R.string.update)
               .withTextColor(R.color.colorPrimary)
               .withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                  override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean
                  {
                     checkUpdate(true)
                     unSelectd(drawerItem)
                     return true
                  }
               }),
            PrimaryDrawerItem()
               .withName(R.string.about)
               .withTextColor(R.color.colorPrimary)
               .withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                  override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean
                  {
                     showAboutDialog()
                     unSelectd(drawerItem)
                     return true
                  }
               }),
            PrimaryDrawerItem()
               .withName(R.string.LoginOut)
               .withTextColor(R.color.colorPrimary)
               .withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                  override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean
                  {
                     unSelectd(drawerItem)
                     return true
                  }
               })
         )
         .
         .build()
   }
}
