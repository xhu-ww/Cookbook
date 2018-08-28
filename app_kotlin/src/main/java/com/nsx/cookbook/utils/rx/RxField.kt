package com.nsx.cookbook.utils.rx

import android.databinding.ObservableField
import io.reactivex.Observable
import android.databinding.Observable as DataBindingObservable

private fun <T> DataBindingObservable.toObservable(getter: () -> T): io.reactivex.Observable<T> =
    Observable.create { emitter ->
        emitter.onNext(getter())

        val callback = object : DataBindingObservable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: DataBindingObservable, propertyId: Int) {
                emitter.onNext(getter())
            }
        }
        emitter.setCancellable { removeOnPropertyChangedCallback(callback) }
        addOnPropertyChangedCallback(callback)
    }

fun <T> ObservableField<T>.toObservable(): Observable<T?> {
    return toObservable { get() }
}

