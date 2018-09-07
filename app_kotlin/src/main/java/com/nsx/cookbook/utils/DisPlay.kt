package com.nsx.cookbook.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Context.dip2px(dipValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun Context.px2dip(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 关闭软键盘
 */
fun Activity.hideSoftKeyBoard() {
    val view = currentFocus ?: return
    val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * 关闭软键盘
 */
fun Dialog.closeKeyBoard() {
    val activity = ownerActivity ?: return
    val view = window!!.peekDecorView() ?: return
    val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(view.windowToken, 0)
}
