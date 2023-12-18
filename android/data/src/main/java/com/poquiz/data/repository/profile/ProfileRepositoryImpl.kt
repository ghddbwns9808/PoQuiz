package com.poquiz.data.repository.profile

import com.poquiz.data.repository.profile.remote.ProfileRemoteDataSource
import com.poquiz.domain.model.User
import com.poquiz.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource
): ProfileRepository {
    override suspend fun updateNickname(user: User): Boolean {
        return profileRemoteDataSource.updateNickname(user)
    }
}