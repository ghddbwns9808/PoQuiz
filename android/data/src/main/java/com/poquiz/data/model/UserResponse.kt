package com.poquiz.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    @SerializedName("userId")
    val userId: String,

    @SerializedName("userPassword")
    val userPassword: String,

    @SerializedName("userNickname")
    val userNickname: String = ""

)
