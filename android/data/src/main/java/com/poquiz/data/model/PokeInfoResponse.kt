package com.poquiz.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeInfoResponse(
    @SerialName("pokemonNumber")
    val pokemonNumber: Int,

    @SerializedName("pokemonType")
    val pokemonType: String,

    @SerializedName("pokemonName")
    val pokemonName: String
)
