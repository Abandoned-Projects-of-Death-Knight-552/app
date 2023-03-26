package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class Dataclass(
    @SerializedName("Media") var Media: Media? = Media()
)