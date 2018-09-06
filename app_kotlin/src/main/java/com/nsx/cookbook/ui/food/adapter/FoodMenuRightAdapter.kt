package com.nsx.cookbook.ui.food.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseRecyclerAdapter
import com.nsx.cookbook.databinding.ItemMenuRightBinding
import com.nsx.cookbook.model.data.FoodChildMenu

class FoodMenuRightAdapter(context: Context) :
    BaseRecyclerAdapter<FoodChildMenu>(context, ViewType.NEITHER) {

    var onChildItemClick: (view: View, menu: FoodChildMenu, name: String) ->
    Unit = { _, _, _ -> }

    override fun onCreateDefaultViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val binding = ItemMenuRightBinding.inflate(inflater, parent, false)
        return DefaultViewHolder(binding)
    }

    override fun onBindDefaultViewHolder(
        holder: RecyclerView.ViewHolder, item: FoodChildMenu, position: Int
    ) {
        holder as DefaultViewHolder
        holder.bindData(item)
    }

    inner class DefaultViewHolder(var binding: ItemMenuRightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = FoodMenuGridAdapter(context)

        fun bindData(menu: FoodChildMenu) {
            binding.menu = menu
            adapter.items = menu.childList
            binding.menuGridView.adapter = adapter
            binding.menuGridView
                .setOnItemClickListener { _, v, position, _ ->
                    onChildItemClick(v, menu, menu.childList[position])
                }
        }
    }
}
