package com.poquiz.domain.usecase.user

import com.poquiz.domain.repository.LoginRepository
import javax.inject.Inject

class IdDuplicatedCheckUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(id: String): Boolean{
        return repository.isDuplicatedId(id)
    }
}