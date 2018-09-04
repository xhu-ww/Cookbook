//package com.nsx.cookbook.model
//
//import android.net.ConnectivityManager
//import okhttp3.Interceptor
//import okhttp3.Response
//import java.net.SocketTimeoutException
//
//class NetworkErrorInterceptor(private val connectivityManager: ConnectivityManager) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        if (connectivityManager.activeNetworkInfo?.isConnectedOrConnecting != true) {
//            throw NetworkException(OfflineError)
//        }
//
//        return try {
//            chain.proceed(chain.request())
//        } catch (e: SocketTimeoutException) {
//            e.printStackTrace()
//            throw NetworkException(TimeoutError)
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            throw NetworkException(UnknownError)
//        }
//    }
//}
