package com.nsx.cookbook.widget

import android.content.Context
import android.support.v7.appcompat.R
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.TextView

class AppToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {
    private var titleTextView: TextView? = null

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        val field = Toolbar::class.java.getDeclaredField("mTitleTextView")
        field.isAccessible = true
        titleTextView = field.get(this) as? TextView
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        titleTextView?.apply {
            val offset = (r - l - right + left) / 2 - left
            layout(left + offset, top, right + offset, bottom)
        }
    }
}
