package com.poquiz.data.repository.login.remote

import android.util.Log
import com.poquiz.data.api.ApiInterface
import com.poquiz.data.api.springApi
import com.poquiz.data.mapper.mapperToUserResponse
import com.poquiz.data.model.UserResponse
import com.poquiz.domain.model.User
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    @springApi private val api: ApiInterface
): LoginRemoteDataSource {
    override suspend fun login(user: User): UserResponse {
        return api.login(mapperToUserResponse( user))
    }

    override suspend fun isDuplicatedId(id: String): Boolean {
        return api.isDuplicatedId(id)
    }

    override suspend fun isDuplicatedNickname(nickname: String): Boolean {
        return api.isDuplicatedNickname(nickname)
    }

    override suspend fun join(user: User): UserResponse {
        return api.join(mapperToUserResponse(user))
    }

}