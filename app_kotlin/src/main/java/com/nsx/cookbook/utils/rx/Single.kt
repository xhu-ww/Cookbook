package com.nsx.cookbook.utils.rx

import android.databinding.ObservableBoolean
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.observeOnMainThread(): Single<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.async(scheduler: Scheduler = Schedulers.io()): Single<T> =
    subscribeOn(scheduler).observeOnMainThread()

fun <T> Single<T>.status(value: ObservableBoolean): Single<T> =
    doFinally { value.set(false) }.doOnSubscribe { value.set(true) }
