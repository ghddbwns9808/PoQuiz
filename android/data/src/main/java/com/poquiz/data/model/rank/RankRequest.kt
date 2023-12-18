package com.poquiz.data.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RankRequest(
    @SerialName("userId")
    val userId: String,

    @SerialName("userNickname")
    val userNickname: String,

    @SerialName("userScore")
    val userScore: Int
)
