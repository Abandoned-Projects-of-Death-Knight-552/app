package com.knight.moonreaderdatabase.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RFHelper {

    val base_url = "https://graphql-anilist.hasura.app/"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val Api: REST by lazy {
        retrofit.create(REST::class.java)
    }
}