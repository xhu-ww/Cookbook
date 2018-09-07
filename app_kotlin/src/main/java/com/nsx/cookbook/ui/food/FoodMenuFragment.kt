package com.nsx.cookbook.ui.food

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseFragment
import com.nsx.cookbook.ui.food.adapter.FoodMenuRightAdapter
import com.nsx.cookbook.ui.food.viewModel.FoodMenuViewModel
import com.nsx.cookbook.utils.databinding.toObservable
import com.nsx.cookbook.utils.rx.bind
import kotlinx.android.synthetic.main.fragment_food_menu.*
import org.kodein.di.generic.instance

class FoodMenuFragment : BaseFragment() {
    private val viewModel: FoodMenuViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search.setOnClickListener {
            val transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!, Pair(searchLayout, getString(R.string.anim_search_view))
                )

            startActivity(
                Intent(activity, FoodActivity::class.java), transitionActivityOptions.toBundle()
            )
        }

        //右边菜单
        val adapter = FoodMenuRightAdapter(context!!)
        adapter.onChildItemClick = { _, _, name ->
            startActivity(FoodActivityIntent())
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        //左侧菜单
        menuListView.onItemClick = { _, parentMenu, _ ->
            adapter.items = parentMenu.list
        }

        viewModel.foodMenu
            .toObservable()
            .subscribe {
                menuListView.items = it
                adapter.items = it[0].list //右侧菜单默认加载第一个
            }
            .bind(this)
    }
}
