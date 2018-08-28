package com.nsx.cookbook.ui.food

import com.nsx.cookbook.di.viewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.contexted
import org.kodein.di.generic.instance

val homeKodein = Kodein.Module {
    bind<FoodViewModel>() with contexted<FoodDetailFragment>().viewModel { FoodViewModel(instance()) }
}
