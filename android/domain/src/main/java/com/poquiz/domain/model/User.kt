package com.poquiz.domain.model

data class User(
    val id: String,
    val pw: String = "",
    val nickname: String
)
