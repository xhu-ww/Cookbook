package com.nsx.cookbook.utils.databinding

import android.databinding.ObservableList

fun <T> ObservableList<T>.setAll(elements: Collection<T>) {
    clear()
    addAll(elements)
}

