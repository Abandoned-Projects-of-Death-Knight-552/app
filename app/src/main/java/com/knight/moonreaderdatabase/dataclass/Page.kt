package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("media") var media: ArrayList<Media> = arrayListOf()
)