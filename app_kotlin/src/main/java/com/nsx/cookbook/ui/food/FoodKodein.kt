package com.nsx.cookbook.ui.food

import com.nsx.cookbook.di.viewModel
import com.nsx.cookbook.ui.food.viewModel.FoodMenuViewModel
import com.nsx.cookbook.ui.food.viewModel.FoodViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.contexted
import org.kodein.di.generic.instance

val foodKodein = Kodein.Module {
    bind<FoodViewModel>() with contexted<FoodDetailFragment>().viewModel {
        FoodViewModel(instance())
    }
    bind<FoodViewModel>() with contexted<FoodsFragment>().viewModel {
        FoodViewModel(instance())
    }
    bind<FoodMenuViewModel>() with contexted<FoodMenuFragment>().viewModel {
        FoodMenuViewModel(instance())
    }
}
