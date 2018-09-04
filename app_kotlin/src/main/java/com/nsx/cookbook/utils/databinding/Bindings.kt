package com.nsx.cookbook.utils.databinding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.nsx.cookbook.R
import com.nsx.cookbook.utils.GlideApp

@BindingAdapter("src")
fun setSrc(view: ImageView, url: String?) {
    if (url == null) {
        view.setImageDrawable(null)
    } else {
        GlideApp.with(view).load(url).placeholder(R.drawable.img_default).into(view)
    }
}

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}
