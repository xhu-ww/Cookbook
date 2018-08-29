package com.nsx.cookbook.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BaseRecyclerAdapter<T>(var context: Context, var viewType: ViewType) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var state: Int = STATE_HIDE



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
