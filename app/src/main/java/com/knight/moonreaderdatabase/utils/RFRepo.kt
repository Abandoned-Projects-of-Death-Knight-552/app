package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.LiveData
import com.knight.moonreaderdatabase.dataclass.Dataclass

class RFRepo {
    suspend fun getPost(): Dataclass {
        return RFHelper.Api.getPost()
    }

     suspend fun getBook(str: String): Dataclass {
        return RFHelper.Api.fetchDetail(str)
    }
}