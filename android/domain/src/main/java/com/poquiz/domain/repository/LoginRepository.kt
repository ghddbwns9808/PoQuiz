package com.poquiz.domain.repository

import com.poquiz.domain.model.User

interface LoginRepository {
    suspend fun login(user: User): User

    suspend fun isDuplicatedId(id: String): Boolean

    suspend fun isDuplicatedNickname(nickname: String): Boolean

    suspend fun join(user: User): User
}