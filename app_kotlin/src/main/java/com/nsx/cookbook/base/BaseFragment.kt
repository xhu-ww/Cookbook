package com.nsx.cookbook.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.nsx.cookbook.di.applicationKodein
import com.nsx.cookbook.utils.logInfo
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.TT

abstract class BaseFragment : Fragment(), KodeinAware {
    override val kodein: Kodein = applicationKodein

    @Suppress("LeakingThis")
    override val kodeinContext: KodeinContext<*> = KodeinContext(TT(javaClass), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logInfo { "---------onViewCreated" }
    }

    override fun onResume() {
        super.onResume()
        logInfo { "---------onResume" }
    }

    override fun onPause() {
        super.onPause()
        logInfo { "---------onPause" }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logInfo { "---------onDestroyView" }
    }
}
