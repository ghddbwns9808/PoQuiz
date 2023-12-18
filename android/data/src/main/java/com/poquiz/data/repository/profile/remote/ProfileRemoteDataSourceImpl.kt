package com.poquiz.data.repository.profile.remote

import com.poquiz.data.api.ApiInterface
import com.poquiz.data.api.springApi
import com.poquiz.data.mapper.mapperToUserResponse
import com.poquiz.domain.model.User
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    @springApi private val api: ApiInterface
): ProfileRemoteDataSource {
    override suspend fun updateNickname(user: User): Boolean {
        return api.updateNickname(mapperToUserResponse(user))
    }
}