package com.nsx.cookbook.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import org.kodein.di.Kodein
import org.kodein.di.TypeToken
import org.kodein.di.bindings.NoArgBindingKodein
import org.kodein.di.bindings.Provider
import org.kodein.di.generic

inline fun <C, reified T : ViewModel> Provider(
    contextType: TypeToken<in C>,
    noinline creator: NoArgBindingKodein<C>.() -> T,
    noinline providerFactory: (C, ViewModelProvider.Factory) -> ViewModelProvider
) = Provider(contextType, generic()) {
    val factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = creator() as T
    }
    providerFactory(context, factory).get(T::class.java)
}

inline fun <C : FragmentActivity, reified T : ViewModel> Kodein.BindBuilder.WithContext<C>.viewModel(
    noinline creator: NoArgBindingKodein<C>.() -> T
) = Provider(contextType, creator) { activity, factory -> ViewModelProviders.of(activity, factory) }

inline fun <C : Fragment, reified T : ViewModel> Kodein.BindBuilder.WithContext<C>.viewModel(
    noinline transformer: C.() -> Any = { this },
    noinline creator: NoArgBindingKodein<C>.() -> T
) = Provider(contextType, creator) { context, factory ->
    val target = context.transformer()
    when (target) {
        is FragmentActivity -> ViewModelProviders.of(target, factory)
        is Fragment -> ViewModelProviders.of(target, factory)
        else -> throw IllegalArgumentException()
    }
}
