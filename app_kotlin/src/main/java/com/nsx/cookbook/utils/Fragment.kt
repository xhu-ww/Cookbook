package com.nsx.cookbook.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

private inline fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().transaction().commit()
}

fun FragmentManager.addFragment(fragment: Fragment, frameId: Int) {
    this.inTransaction { add(frameId, fragment, fragment.javaClass.name) }
}

fun FragmentManager.addFragmentToBackStack(fragment: Fragment, frameId: Int) {
    this.inTransaction { add(frameId, fragment).addToBackStack(fragment.javaClass.name) }
}

fun FragmentManager.replaceFragment(fragment: Fragment, frameId: Int) {
    this.inTransaction { replace(frameId, fragment) }
}

fun FragmentManager.hideFragment(fragment: Fragment) {
    this.inTransaction { hide(fragment) }
}

fun FragmentManager.showFragment(fragment: Fragment) {
    this.inTransaction { show(fragment) }
}
