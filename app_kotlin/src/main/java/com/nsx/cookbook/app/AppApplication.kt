package com.nsx.cookbook.app

import android.app.Application
import com.nsx.cookbook.di.initApplicationKodein
import com.tencent.bugly.crashreport.CrashReport

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initApplicationKodein(this)
        CrashReport.initCrashReport(this, "eb6b631a23", false)
    }
}
