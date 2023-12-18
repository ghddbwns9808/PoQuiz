package com.poquiz.data.repository.login

import com.poquiz.data.mapper.mapperToUser
import com.poquiz.data.repository.login.remote.LoginRemoteDataSource
import com.poquiz.domain.model.User
import com.poquiz.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(user: User): User {
        return mapperToUser(remoteDataSource.login(user))
    }

    override suspend fun isDuplicatedId(id: String): Boolean {
        return remoteDataSource.isDuplicatedId(id)
    }

    override suspend fun isDuplicatedNickname(nickname: String): Boolean {
        return remoteDataSource.isDuplicatedNickname(nickname)
    }

    override suspend fun join(user: User): User {
        return mapperToUser(remoteDataSource.join(user))
    }
}