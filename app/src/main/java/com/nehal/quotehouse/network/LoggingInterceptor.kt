package com.nehal.quotehouse.network

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor() {
    fun setLogging(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("Retrofit", message)
            }
        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return loggingInterceptor
    }
}
