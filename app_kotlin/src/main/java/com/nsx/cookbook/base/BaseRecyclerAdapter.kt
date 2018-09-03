package com.nsx.cookbook.base

import android.content.Context
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.nsx.cookbook.R

open abstract class BaseRecyclerAdapter<T>(var context: Context, var viewType: ViewType) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var state: Int = STATE_HIDE

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    open var onLoadingHeaderCallBack: OnLoadingHeaderCallBack? = null

    var onItemClick: (view: View, item: T, position: Int) -> Unit = { _, _, _ -> }
    var onItemLongClick: (view: View, item: T, position: Int) -> Boolean = { _, _, _ -> true }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                if (onLoadingHeaderCallBack != null)
                    onLoadingHeaderCallBack!!.onCreateHeaderHolder(parent)
                else
                    throw IllegalArgumentException("No Holder that matches the viewType")
            }
            VIEW_TYPE_FOOTER -> FooterViewHolder(
                inflater.inflate(R.layout.recycle_footer_view, parent, false)
            )
            else -> onCreateDefaultViewHolder(parent, viewType).apply {
                itemView.tag = this
                itemView.setOnLongClickListener {
                    onItemLongClick(it, items[adapterPosition], adapterPosition)
                }
                itemView.setOnClickListener {
                    onItemClick(it, items[adapterPosition], adapterPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (viewType == ViewType.ONLY_FOOTER || viewType == ViewType.ONLY_HEADER) {
            items.size + 1
        } else if (viewType == ViewType.BOTH_HEADER_FOOTER) {
            items.size + 2
        } else
            items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> onLoadingHeaderCallBack?.onBindHeaderHolder(holder, position)
            VIEW_TYPE_FOOTER -> {
                holder as FooterViewHolder
                holder.itemView.visibility = View.VISIBLE
                when (state) {
                    STATE_INVALID_NETWORK -> {
                        holder.textView.text = context.getString(R.string.state_network_error)
                        holder.progressBar.visibility = View.GONE
                    }
                    STATE_LOAD_MORE, STATE_LOADING -> {
                        holder.textView.text = context.getString(R.string.state_loading)
                        holder.progressBar.visibility = View.VISIBLE
                    }
                    STATE_NO_MORE -> {
                        holder.textView.text = context.getString(R.string.state_not_more)
                        holder.progressBar.visibility = View.GONE
                    }
                    STATE_REFRESHING -> {
                        holder.textView.text = context.getString(R.string.state_refreshing)
                        holder.progressBar.visibility = View.GONE
                    }
                    STATE_LOAD_ERROR -> {
                        holder.textView.text = context.getString(R.string.state_load_error)
                        holder.progressBar.visibility = View.GONE
                    }
                    STATE_HIDE -> holder.itemView.visibility = View.GONE
                }
            }
            else -> onBindDefaultViewHolder(holder, items[getIndex(position)], position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 &&
            (viewType == ViewType.ONLY_HEADER || viewType == ViewType.BOTH_HEADER_FOOTER)
        )
            return VIEW_TYPE_HEADER

        if (position + 1 == itemCount &&
            (viewType == ViewType.ONLY_FOOTER || viewType == ViewType.BOTH_HEADER_FOOTER)
        )
            return VIEW_TYPE_FOOTER

        return VIEW_TYPE_NORMAL
    }

    /**
     * 当添加到RecyclerView时获取GridLayoutManager布局管理器，修正header和footer显示整行
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == VIEW_TYPE_HEADER ||
                        getItemViewType(position) == VIEW_TYPE_FOOTER
                    )
                        manager.spanCount
                    else
                        1
                }
            }
        }
    }

    /**
     * 当RecyclerView在windows活动时获取StaggeredGridLayoutManager布局管理器，修正header和footer显示整行
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        val lp = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            when (viewType) {
                ViewType.ONLY_HEADER -> lp.isFullSpan = holder.layoutPosition == 0
                ViewType.ONLY_FOOTER -> lp.isFullSpan = holder.layoutPosition == items.size + 1
                ViewType.BOTH_HEADER_FOOTER ->
                    lp.isFullSpan =
                            holder.layoutPosition == 0 || holder.layoutPosition == items.size + 1
            }
        }
    }

    private fun getIndex(position: Int): Int {
        return if (viewType == ViewType.ONLY_HEADER || viewType == ViewType.BOTH_HEADER_FOOTER)
            position - 1
        else
            position
    }

    /**
     * 创建 普通清空下的 ViewHolder
     */
    abstract fun onCreateDefaultViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder

    /**
     * bind 普通情况下的 ViewHolder
     */
    abstract fun onBindDefaultViewHolder(holder: RecyclerView.ViewHolder, item: T, position: Int)

    /**
     * 设置当前 Adapter显示的 加载状态
     */
    fun setState(state: Int, isUpdate: Boolean = true) {
        this.state = state
        if (isUpdate) updateItem(itemCount - 1)
    }

    private fun updateItem(position: Int) {
        if (itemCount > position) Handler().post { notifyItemChanged(position) }
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var progressBar = view.findViewById<ProgressBar>(R.id.footer_progressBar)!!
        var textView = view.findViewById<TextView>(R.id.footer_textView)!!
    }

    interface OnLoadingHeaderCallBack {
        fun onCreateHeaderHolder(parent: ViewGroup): RecyclerView.ViewHolder

        fun onBindHeaderHolder(holder: RecyclerView.ViewHolder, position: Int)
    }

    enum class ViewType {
        ONLY_HEADER, ONLY_FOOTER, BOTH_HEADER_FOOTER, NEITHER
    }

    companion object {
        val STATE_NO_MORE = 1
        val STATE_LOAD_MORE = 2
        val STATE_INVALID_NETWORK = 3
        val STATE_HIDE = 5
        val STATE_REFRESHING = 6
        val STATE_LOAD_ERROR = 7
        val STATE_LOADING = 8

        private val VIEW_TYPE_NORMAL = -1
        private val VIEW_TYPE_HEADER = -2
        private val VIEW_TYPE_FOOTER = -3
    }
}
