package com.nehal.quotehouse.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("X-Parse-Application-Id", "W7zZ0ACPttuZucYySAMbcX8G08W1uORZ0uS9mKVl")
            .header("X-Parse-REST-API-Key", "LSFvb3Dmq3ui6nYhsYPDK3XJruO8IvCsJ8vs2SfR")
            .header("content-type", "application/json")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}