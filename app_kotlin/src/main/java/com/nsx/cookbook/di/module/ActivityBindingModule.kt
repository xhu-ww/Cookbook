package com.nsx.cookbook.di.module

import dagger.Module
import com.nsx.cookbook.di.scope.ActivityScope
import com.nsx.cookbook.ui.MainActivity
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    @ActivityScope
    abstract fun mainActivity(): MainActivity
}