package com.nsx.cookbook.di

import android.content.Context
import android.content.res.AssetManager
import com.nsx.cookbook.model.service.FoodService
import com.nsx.cookbook.ui.food.foodKodein
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

lateinit var applicationKodein: Kodein

fun initApplicationKodein(context: Context) {
    applicationKodein = Kodein {
        import(foodKodein)

        bind<Context>() with instance(context)
        bind<FoodService>() with singleton { FoodService() }
        bind<AssetManager>() with singleton { context.assets }
    }
}
