package com.nsx.cookbook.di.app

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class AppApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

    }
}
