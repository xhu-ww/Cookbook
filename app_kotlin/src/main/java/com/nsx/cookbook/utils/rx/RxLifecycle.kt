package com.nsx.cookbook.utils.rx

import android.arch.lifecycle.Lifecycle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nsx.cookbook.ui.ObservableLifecycle
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy

private fun Lifecycle.toObservable(): Observable<Lifecycle.State> =
    RxJavaPlugins.onAssembly<Lifecycle.State>(ObservableLifecycle(this))

fun <T> Observable<T>.bindUtil(lifecycle: Lifecycle, state: Lifecycle.State): Observable<T> =
    takeUntil(lifecycle.toObservable().filter { v -> v == state })

fun <T> Observable<T>.bind(lifecycle: Lifecycle): Observable<T> =
    takeUntil(lifecycle.toObservable().ignoreElements().toObservable<Any>())

fun <T> Observable<T>.bind(activity: AppCompatActivity): Observable<T> =
    bind(activity.lifecycle)

fun <T> Observable<T>.bind(fragment: Fragment): Observable<T> = bind(fragment.lifecycle)

fun Disposable.bind(activity: AppCompatActivity) {
    activity.lifecycle.toObservable().subscribeBy(onComplete = this::dispose)
}

fun Disposable.bind(fragment: Fragment) {
    fragment.lifecycle.toObservable().subscribeBy(onComplete = this::dispose)
}
