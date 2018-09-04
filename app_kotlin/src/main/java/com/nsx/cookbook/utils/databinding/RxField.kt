package com.nsx.cookbook.utils.databinding

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
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

fun ObservableBoolean.toObservable(): Observable<Boolean> = toObservable(this::get)

fun <T> ObservableList<T>.toObservable(): Observable<List<T>> = Observable.create { emitter ->
    emitter.onNext(this)

    val callback =
        ObservableListCallback<T> { emitter.onNext(it) }
    emitter.setCancellable { removeOnListChangedCallback(callback) }
    addOnListChangedCallback(callback)
}

private class ObservableListCallback<T>(private val callback: (List<T>) -> Unit) :
    ObservableList.OnListChangedCallback<ObservableList<T>>() {

    override fun onChanged(sender: ObservableList<T>) {
        callback(sender)
    }

    override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        callback(sender)
    }

    override fun onItemRangeInserted(
        sender: ObservableList<T>,
        positionStart: Int, itemCount: Int
    ) {
        callback(sender)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<T>,
        fromPosition: Int, toPosition: Int, itemCount: Int
    ) {
        callback(sender)
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        callback(sender)
    }
}