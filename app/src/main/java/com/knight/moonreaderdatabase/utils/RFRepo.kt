package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.LiveData
import com.knight.moonreaderdatabase.dataclass.AnResp
import com.knight.moonreaderdatabase.dataclass.Dataclass

class RFRepo {
     suspend fun getBook(str: String): Dataclass {
        return RFHelper.Api.fetchDetail(str)
    }

    suspend fun getSearchRes(str: String): AnResp {
        return RFHelper.Api.fetchPage(str)
    }
}