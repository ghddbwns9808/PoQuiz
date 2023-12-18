package com.poquiz.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeImageResponse(
    @SerialName("sprites")
    val sprites: Sprites
)
