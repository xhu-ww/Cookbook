package com.nsx.cookbook.ui.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.nsx.cookbook.R

class FoodMenuGridAdapter(val context: Context) : BaseAdapter() {
    var items: List<String> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_menu_gridview, parent, false);
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.bindData(name = items[position])
        return view
    }

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = items.size

    internal class ViewHolder(val view: View) {

        fun bindData(name: String) {
            val textView = view.findViewById<TextView>(R.id.nameTextView)
            textView.text = name
        }
    }
}
