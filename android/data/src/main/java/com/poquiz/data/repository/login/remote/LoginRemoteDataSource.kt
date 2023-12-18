package com.poquiz.data.repository.login.remote

import com.poquiz.data.model.UserResponse
import com.poquiz.domain.model.User

interface LoginRemoteDataSource {
    suspend fun login(user: User): UserResponse

    suspend fun isDuplicatedId(id: String): Boolean

    suspend fun isDuplicatedNickname(nickname: String): Boolean

    suspend fun join(user: User): UserResponse
}