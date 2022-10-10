package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("english") var english: String? = null,
    @SerializedName("native") var native: String? = null,
    @SerializedName("userPreferred") var userPreferred: String? = null
)