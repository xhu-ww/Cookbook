package com.nsx.cookbook.widget

import android.support.v7.widget.RecyclerView
import android.graphics.Rect
import android.view.View

/**
 * RecyclerView 的 Grid 分割线
 */
class GridSpacingItemDecoration(var spanCount: Int, var spacing: Int, var includeEdge: Boolean) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) outRect.top = spacing

            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

            if (position >= spanCount) outRect.top = spacing
        }
    }
}
