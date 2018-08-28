package com.nsx.cookbook.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseActivity
import com.nsx.cookbook.ui.food.FoodDetailFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragment(FoodDetailFragment::class.java)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.home
    }

    private fun showFragment(clazz: Class<*>) {
        val transition = supportFragmentManager.beginTransaction()
        supportFragmentManager.fragments.forEach { transition.hide(it) }
        var fragment = supportFragmentManager.findFragmentByTag(clazz.name)
        if (fragment == null) {
            fragment = clazz.newInstance() as Fragment
            transition.add(R.id.root, fragment, clazz.name)
        } else {
            transition.show(fragment)
        }
        transition.commitNowAllowingStateLoss()
    }
}
