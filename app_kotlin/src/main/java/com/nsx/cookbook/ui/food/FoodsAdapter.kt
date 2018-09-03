package com.nsx.cookbook.ui.food

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nsx.cookbook.base.BaseRecyclerAdapter
import com.nsx.cookbook.model.data.FoodDetailBean

class FoodsAdapter(context: Context, viewType: ViewType) :
    BaseRecyclerAdapter<FoodDetailBean>(context, viewType) {

    override fun onCreateDefaultViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindDefaultViewHolder(
        holder: RecyclerView.ViewHolder,
        item: FoodDetailBean,
        position: Int
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class DefaultViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

//        fun bindData(itemData: DeviceCheckOrder) {
//            val itemBinding = binding as DmItemExceptionDocumentBinding
//            itemBinding.setData(itemData)
//            itemBinding.tvStatus.setText(itemData.getStatus().getDisplayContent())
//            itemBinding.tvStatus.setBackgroundColor(
//                mContext.getResources().getColor(itemData.getStatus().getDisplayColor())
//            )
//        }
    }
}
