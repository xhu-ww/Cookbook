package com.nsx.cookbook.di

import android.content.Context
import com.nsx.cookbook.model.service.FoodService
import com.nsx.cookbook.ui.food.homeKodein
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

lateinit var applicationKodein: Kodein

fun initApplicationKodein(context: Context) {
    applicationKodein = Kodein {
        import(homeKodein)

        bind<Context>() with instance(context)
        bind<FoodService>() with singleton { FoodService() }
    }
}
