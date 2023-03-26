package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName


data class Media(
    @SerializedName("title") var title: Title? = Title(),
    @SerializedName("synonyms") var synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("coverImage") var coverImage: CoverImage? = CoverImage(),
    @SerializedName("description") var description: String? = null
)