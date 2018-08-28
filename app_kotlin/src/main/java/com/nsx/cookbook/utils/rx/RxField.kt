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

data class NullableValue<T>(val value: T?)

fun <T> ObservableField<T>.toNullableObservable(): Observable<NullableValue<T>> =
    toObservable { NullableValue(get()) }

//如果明确知道 ObservableField 内的值不为空 则 传false
fun <T> ObservableField<T>.toObservable(nullable: Boolean = true): Observable<T> = if (nullable) {
    toNullableObservable().filter { it.value != null }.map { it.value }
} else {
    toObservable { get()!! }
}
