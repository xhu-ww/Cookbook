package com.nsx.cookbook.utils

import android.util.Log
import com.nsx.cookbook.BuildConfig

inline fun log(
    tag: String,
    message: Any?,
    thr: Throwable?,
    f: (String, String) -> Unit,
    fThrowable: (String, String, Throwable) -> Unit
) {
    if (BuildConfig.DEBUG) {
        val newTag = if (tag.length <= 23) tag else tag.substring(0, 23)
        if (thr != null) {
            fThrowable(newTag, message?.toString() ?: "null", thr)
        } else {
            f(newTag, message?.toString() ?: "null")
        }
    }
}

inline fun Any.log(
    message: Any?,
    thr: Throwable?,
    f: (String, String) -> Unit,
    fThrowable: (String, String, Throwable) -> Unit
) {
    log(javaClass.simpleName, message, thr, f, fThrowable)
}

inline fun Any.logVerbose(tr: Throwable? = null, message: () -> Any?) {
    log(message(), tr, { t, msg -> Log.v(t, msg) }, { t, msg, thr -> Log.v(t, msg, thr) })
}

inline fun logVerbose(tag: String, tr: Throwable? = null, message: () -> Any?) {
    log(tag, message(), tr, { t, msg -> Log.v(t, msg) }, { t, msg, thr -> Log.v(t, msg, thr) })
}

inline fun Any.logDebug(tr: Throwable? = null, message: () -> Any?) {
    log(message(), tr, { t, msg -> Log.d(t, msg) }, { t, msg, thr -> Log.d(t, msg, thr) })
}

inline fun logDebug(tag: String, tr: Throwable? = null, message: () -> Any?) {
    log(tag, message(), tr, { t, msg -> Log.d(t, msg) }, { t, msg, thr -> Log.d(t, msg, thr) })
}

inline fun Any.logInfo(tr: Throwable? = null, message: () -> Any?) {
    log(message(), tr, { t, msg -> Log.i(t, msg) }, { t, msg, thr -> Log.i(t, msg, thr) })
}

inline fun logInfo(tag: String, tr: Throwable? = null, message: () -> Any?) {
    log(tag, message(), tr, { t, msg -> Log.i(t, msg) }, { t, msg, thr -> Log.i(t, msg, thr) })
}

inline fun Any.logWarn(tr: Throwable? = null, message: () -> Any?) {
    log(message(), tr, { t, msg -> Log.w(t, msg) }, { t, msg, thr -> Log.w(t, msg, thr) })
}

inline fun logWarn(tag: String, tr: Throwable? = null, message: () -> Any?) {
    log(tag, message(), tr, { t, msg -> Log.w(t, msg) }, { t, msg, thr -> Log.w(t, msg, thr) })
}

inline fun Any.logError(tr: Throwable? = null, message: () -> Any?) {
    log(message(), tr, { t, msg -> Log.e(t, msg) }, { t, msg, thr -> Log.e(t, msg, thr) })
}

inline fun logError(tag: String, tr: Throwable? = null, message: () -> Any?) {
    log(tag, message(), tr, { t, msg -> Log.e(t, msg) }, { t, msg, thr -> Log.e(t, msg, thr) })
}
