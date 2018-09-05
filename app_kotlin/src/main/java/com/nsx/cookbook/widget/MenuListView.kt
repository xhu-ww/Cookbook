package com.nsx.cookbook.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.nsx.cookbook.R
import com.nsx.cookbook.model.data.FoodParentMenu

/**
 * 食谱左侧菜单
 */
class MenuListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var textNormalColor: Int = 0
    private var textSelectColor: Int = 0
    private var textBackNormalColor: Int = 0
    private var textBackSelectColor: Int = 0
    private var drawableLeft: Drawable? = null

    //上一次选择的 Item
    private var lastItem = -1

    var items: List<FoodParentMenu> = ArrayList()
        set(value) {
            for (i in value.indices) {
                addView(getChild(value[i], i))
            }
        }
    var onItemClick: (view: View, item: FoodParentMenu, position: Int) -> Unit = { _, _, _ -> }

    init {
        val array =
            context.obtainStyledAttributes(attrs, R.styleable.MenuListView, defStyleAttr, 0)
        for (i in 0 until array.length()) {
            val attr = array.getIndex(i)
            when (attr) {
                R.styleable.MenuListView_textNormalColor ->
                    textNormalColor = array.getColor(attr, Color.parseColor("#cccccc"))
                R.styleable.MenuListView_textSelectColor ->
                    textSelectColor = array.getColor(attr, Color.parseColor("#ff0000"))
                R.styleable.MenuListView_textBackNormalColor ->
                    textBackNormalColor = array.getColor(attr, Color.parseColor("#ffffff"))
                R.styleable.MenuListView_textBackSelectColor ->
                    textBackSelectColor = array.getColor(attr, Color.parseColor("#cccccc"))
                R.styleable.MenuListView_drawableLeft -> drawableLeft = array.getDrawable(attr)
            }
        }
        array.recycle()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (lastItem == -1 && this.childCount > 0) {
            lastItem = 0
            setTextViewBackground(this.getChildAt(0) as TextView, true)
        }
    }

    private fun getChild(foodParentMenu: FoodParentMenu, position: Int): TextView {
        val child = LayoutInflater.from(context)
            .inflate(R.layout.item_menu_listview, this, false) as TextView

        child.text = foodParentMenu.group
        child.setOnClickListener { v ->
            if (position != lastItem) {
                setTextViewBackground(getChildAt(lastItem) as TextView, false)
                setTextViewBackground(v as TextView, true)
                lastItem = position
                onItemClick(v, foodParentMenu, position)
            }
        }
        if (position == 0) {
            lastItem = 0
            setTextViewBackground(child, true)
        }
        return child
    }

    private fun setTextViewBackground(v: TextView, hasFocus: Boolean) {
        if (hasFocus) {
            v.setBackgroundColor(textBackSelectColor)
            v.setTextColor(textSelectColor)
            if (drawableLeft != null) {
                drawableLeft!!.setBounds(
                    0, 0, drawableLeft!!.minimumWidth, v.measuredHeight - 20
                )
                v.setCompoundDrawables(drawableLeft, null, null, null)
                v.compoundDrawablePadding = 15
            }
        } else {
            v.setBackgroundColor(textBackNormalColor)
            v.setTextColor(textNormalColor)
            v.setCompoundDrawables(null, null, null, null)
            v.compoundDrawablePadding = 0
        }
    }
}
