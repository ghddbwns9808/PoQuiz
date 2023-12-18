package com.poquiz.data.repository.profile.remote

import com.poquiz.domain.model.User

interface ProfileRemoteDataSource {

    suspend fun updateNickname(user: User): Boolean
}