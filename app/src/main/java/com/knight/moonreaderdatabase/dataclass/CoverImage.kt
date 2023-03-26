package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class CoverImage(
    @SerializedName("large") var large: String? = null,
    @SerializedName("extraLarge") var extraLarge: String? = null
)