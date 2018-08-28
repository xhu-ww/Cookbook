package com.nsx.cookbook.app

import android.app.Application
import com.nsx.cookbook.di.initApplicationKodein

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initApplicationKodein(this)
    }
}
