package com.nsx.cookbook.di.component

import android.app.Application
import com.nsx.cookbook.di.app.AppApplication
import com.nsx.cookbook.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import dagger.android.AndroidInjector

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<AppApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}