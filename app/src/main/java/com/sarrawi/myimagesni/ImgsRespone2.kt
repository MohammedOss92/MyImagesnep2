package com.sarrawi.myimagesni

import com.google.gson.annotations.SerializedName


data class ImgsRespone2(
    @SerializedName("ImgsModel")
    val results: List<ImgsModel>
)

