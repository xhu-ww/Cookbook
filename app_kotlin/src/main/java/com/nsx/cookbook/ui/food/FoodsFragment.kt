package com.nsx.cookbook.ui.food

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseFragment
import com.nsx.cookbook.base.BaseRecyclerAdapter
import com.nsx.cookbook.ui.food.adapter.FoodsAdapter
import com.nsx.cookbook.ui.food.viewModel.FoodViewModel
import com.nsx.cookbook.utils.databinding.toObservable
import com.nsx.cookbook.utils.rx.bind
import kotlinx.android.synthetic.main.fragment_foods.*
import org.kodein.di.generic.instance
import com.nsx.cookbook.utils.dip2px
import com.nsx.cookbook.widget.GridSpacingItemDecoration


class FoodsFragment : BaseFragment() {
    private val viewModel: FoodViewModel by instance()
    private lateinit var adapter: FoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_foods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FoodsAdapter(context!!)

        refreshLayout.setRefreshLayoutListener(this::onRefreshing, this::onLoadMore)
        recyclerView.layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.adapter = adapter

        val spacing = context!!.dip2px(15f)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, true))

        bind()
        viewModel.refreshFoods(2)
    }

    private fun bind() {
        viewModel.foods
            .toObservable()
            .subscribe { adapter.items = it }
            .bind(this)

        viewModel.processing
            .toObservable()
            .filter { !it }
            .subscribe { refreshLayout.onComplete() }
            .bind(this)
    }

    private fun onRefreshing() {
        viewModel.refreshFoods(2)
        adapter.setState(BaseRecyclerAdapter.STATE_HIDE, true)
    }

    private fun onLoadMore() {
        viewModel.loadMoreFoods(2)
        adapter.setState(BaseRecyclerAdapter.STATE_LOAD_MORE, true)
    }

    companion object {
        private const val spanCount = 2
    }
}
