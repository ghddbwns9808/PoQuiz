package com.poquiz.domain.repository

import com.poquiz.domain.model.User

interface ProfileRepository {

    suspend fun updateNickname(user: User): Boolean
}