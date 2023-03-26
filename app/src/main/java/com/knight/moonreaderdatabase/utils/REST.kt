package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.LiveData
import com.knight.moonreaderdatabase.dataclass.AnResp
import com.knight.moonreaderdatabase.dataclass.Dataclass
import retrofit2.http.GET
import retrofit2.http.Query

interface REST {
    @GET("api/rest/search")
    suspend fun fetchDetail(@Query("search") search: String) : Dataclass

    @GET("api/rest/searchfor")
    suspend fun fetchPage(@Query("string") string: String) : AnResp
}