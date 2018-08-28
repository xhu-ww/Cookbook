package com.nsx.cookbook.utils.rx

import android.databinding.ObservableBoolean
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.async(scheduler: Scheduler = Schedulers.io()): Observable<T> =
    subscribeOn(scheduler).observeOnMainThread()

fun <T> Observable<T>.status(value: ObservableBoolean): Observable<T> =
    doFinally { value.set(false) }.doOnSubscribe { value.set(true) }