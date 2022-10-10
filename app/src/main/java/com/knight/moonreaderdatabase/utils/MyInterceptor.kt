package com.knight.moonreaderdatabase.utils

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-hasura-admin-secret", "lXp52y4u8mkt35RfWhMhClYQ7ZKJPPoBFORtqLaOLB66zEcvN2ZncyydvpxxwdAL")
            .addHeader("content-type", "application/json")
            .build()
        return chain.proceed(request)
    }
}