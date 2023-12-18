package com.poquiz.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    @SerializedName("front_default")
    val front_default: String
)
