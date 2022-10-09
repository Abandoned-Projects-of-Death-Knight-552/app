package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class Dataclass(
    @SerializedName("Media") var Media: Media? = Media()
)

data class Media(
    @SerializedName("title") var title: Title? = Title(),
    @SerializedName("synonyms") var synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("coverImage") var coverImage: CoverImage? = CoverImage(),
    @SerializedName("description") var description: String? = null
)

data class CoverImage(
    @SerializedName("large") var large: String? = null
)

data class Title(
    @SerializedName("english") var english: String? = null,
    @SerializedName("native") var native: String? = null,
    @SerializedName("userPreferred") var userPreferred: String? = null
)