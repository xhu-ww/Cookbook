package com.nsx.cookbook.ui.food

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsx.cookbook.R
import com.nsx.cookbook.base.BaseFragment
import com.nsx.cookbook.databinding.FragmentFoodDetailBinding
import com.nsx.cookbook.utils.databinding.toObservable
import com.nsx.cookbook.utils.rx.bind
import org.kodein.di.generic.instance

class FoodsFragment : BaseFragment() {
    private val viewModel: FoodViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_foods, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshFoods(5)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentFoodDetailBinding = DataBindingUtil.bind(view)!!

        viewModel.food
            .toObservable()
            .subscribe {
            }
            .bind(this)
    }
}
