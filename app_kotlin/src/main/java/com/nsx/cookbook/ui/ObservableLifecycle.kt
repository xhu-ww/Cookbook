package com.nsx.cookbook.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper
import java.util.concurrent.atomic.AtomicReference

class ObservableLifecycle(private val lifecycle: Lifecycle) : Observable<Lifecycle.State>() {
    override fun subscribeActual(observer: Observer<in Lifecycle.State>) {
        observer.onSubscribe(LifecycleObserver(observer, lifecycle))
    }
}

private class LifecycleObserver(
    private val actual: Observer<in Lifecycle.State>,
    private val lifecycle: Lifecycle
) : AtomicReference<Disposable>(), Disposable, android.arch.lifecycle.LifecycleObserver {
    init {
        actual.onNext(lifecycle.currentState)
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onReceiveEvent() {
        if (isDisposed) return

        actual.onNext(lifecycle.currentState)

        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            actual.onComplete()
            lifecycle.removeObserver(this)
        }
    }

    override fun dispose() {
        DisposableHelper.dispose(this)

        lifecycle.removeObserver(this)
    }

    override fun isDisposed(): Boolean {
        return DisposableHelper.isDisposed(get())
    }
}

