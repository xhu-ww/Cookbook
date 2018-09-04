package com.nsx.cookbook.utils.rx

import android.databinding.ObservableBoolean
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Maybe<T>.observeOnMainThread(): Maybe<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.async(scheduler: Scheduler = Schedulers.io()): Maybe<T> =
    subscribeOn(scheduler).observeOnMainThread()

fun <T> Maybe<T>.status(value: ObservableBoolean): Maybe<T> =
    doFinally { value.set(false) }.doOnSubscribe { value.set(true) }

