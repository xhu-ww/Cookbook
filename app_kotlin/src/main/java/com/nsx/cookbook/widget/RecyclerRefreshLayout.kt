package com.nsx.cookbook.widget

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.nsx.cookbook.R
import com.nsx.cookbook.utils.logInfo

/**
 * 下拉刷新上拉加载控件，目前适用于RecyclerView
 */
class RecyclerRefreshLayout : SwipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener {

    private var recycleView: RecyclerView? = null
    private var touchSlop: Int = 0
    private var onLoading = false
    private var canLoadMore = true
    private var hasMore = true

    private var yDown: Float = 0F
    private var lastY: Float = 0F

    private var onRefreshing: () -> Unit = {}
    private var onLoadMore: () -> Unit = {}

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setOnRefreshListener(this)
        setColorSchemeColors(resources.getColor(R.color.main_red))
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onRefresh() {
        if (onLoading) {
            isRefreshing = false
        } else {
            onRefreshing()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (recycleView == null) {
            getRecycleView()
        }
    }

    private fun getRecycleView() {
        if (childCount < 0) return
        var childView = getChildAt(0)
        if (childView !is RecyclerView) {
            childView = findViewById(R.id.recyclerView)
        }
        if (childView is RecyclerView) {
            recycleView = childView
            recycleView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    if (canLoad() && canLoadMore) loadData()
                }
            })
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> yDown = ev.rawY
            MotionEvent.ACTION_MOVE -> lastY = ev.rawY
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private fun loadData() {
        setOnLoading(true)
        onLoadMore()
    }

    /**
     * 设置正在加载
     *
     * @param loading 设置正在加载
     */
    private fun setOnLoading(loading: Boolean) {
        onLoading = loading
        if (!onLoading) {
            yDown = 0F
            lastY = 0F
        }
    }

    /**
     * 获取RecyclerView可见的最后一项
     *
     */
    private fun getLastVisiblePosition(): Int {
        val layoutManager = recycleView?.layoutManager ?: return 0
        return when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is StaggeredGridLayoutManager -> {
                val lastPositions =
                    layoutManager.findLastVisibleItemPositions(IntArray(layoutManager.spanCount))
                getMaxPosition(lastPositions)
            }
            else -> layoutManager.itemCount - 1
        }
    }

    /**
     * 获得最大的位置
     */
    private fun getMaxPosition(positions: IntArray): Int {
        var maxPosition = Integer.MIN_VALUE
        positions.forEach { maxPosition = Math.max(maxPosition, it) }
        return maxPosition
    }

    /**
     * 判断是否到了最底部
     */
    private fun isScrollBottom(): Boolean {
        val adapter = recycleView?.adapter ?: return false
        return getLastVisiblePosition() == adapter.itemCount - 1
    }

    /**
     * 是否可以加载更多, 条件是到了最底部
     */
    private fun canLoad(): Boolean = isScrollBottom() && !onLoading && isPullUp() && hasMore

    /**
     * 是否是上拉操作
     */
    private fun isPullUp(): Boolean = (yDown - lastY) >= touchSlop

    /**
     * 是否可加载更多
     */
    fun setCanLoadMore(canLoadMore: Boolean) {
        this.canLoadMore = canLoadMore
    }

    /**
     * 加载完成 后调用
     */
    fun onComplete() {
        setOnLoading(false)
        isRefreshing = false
        hasMore = true
    }

    fun setRefreshLayoutListener(onRefreshing: () -> Unit = {}, onLoadMore: () -> Unit = {}) {
        this.onRefreshing = onRefreshing
        this.onLoadMore = onLoadMore
    }
}
