package com.poquiz.domain.usecase.user

import com.poquiz.domain.repository.LoginRepository
import javax.inject.Inject

class NicknameDuplicatedCheckUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(nickname: String): Boolean{
        return repository.isDuplicatedNickname(nickname)
    }
}