package com.knight.moonreaderdatabase.dataclass

import com.google.gson.annotations.SerializedName

data class AnResp(
    @SerializedName("Page") var page: Page = Page()
)