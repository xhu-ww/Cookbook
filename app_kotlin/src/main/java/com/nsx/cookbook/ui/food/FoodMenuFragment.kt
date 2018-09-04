package com.nsx.cookbook.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseFragment
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
//
//        val adapter = TagCloudAdapter()
//        adapter.onTagClick = { _, firstMenu, _ ->
//            Toast.makeText(context!!, firstMenu.name, Toast.LENGTH_SHORT).show()
//        }
//        tagCloud.setAdapter(adapter)
//
//        viewModel.foodMenu
//            .toObservable()
//            .subscribe { adapter.firstMenus = it }
//            .bind(this)
    }
}
