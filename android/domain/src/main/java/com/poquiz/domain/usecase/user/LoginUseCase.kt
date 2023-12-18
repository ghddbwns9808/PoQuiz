package com.poquiz.domain.usecase.user

import com.poquiz.domain.model.User
import com.poquiz.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: User): User{
        return repository.login(user)
    }
}