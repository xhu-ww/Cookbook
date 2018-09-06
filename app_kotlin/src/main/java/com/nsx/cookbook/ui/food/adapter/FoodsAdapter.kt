package com.nsx.cookbook.ui.food.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseRecyclerAdapter
import com.nsx.cookbook.databinding.ItemFoodsBinding
import com.nsx.cookbook.model.data.Food

class FoodsAdapter(context: Context) : BaseRecyclerAdapter<Food>(context, ViewType.ONLY_FOOTER) {

    override fun onCreateDefaultViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val binding = ItemFoodsBinding.inflate(inflater, parent, false)
        return DefaultViewHolder(binding)
    }

    override fun onBindDefaultViewHolder(
        holder: RecyclerView.ViewHolder, food: Food, position: Int
    ) {
        (holder as? DefaultViewHolder)?.bindData(food)
    }

    class DefaultViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(food: Food) {
            val itemBinding = binding as ItemFoodsBinding
            itemBinding.food = food
        }
    }
}
