package com.knight.moonreaderdatabase.utils

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-hasura-admin-secret", "XEW4IlI66SHi5CdGWtf71zVeNW5gZo2wRBvWU3jfzn9PSMwM0KQ2zeaUg90tJO43")
            .addHeader("content-type", "application/json")
            .build()
        return chain.proceed(request )
    }
}