package com.poquiz.domain.usecase.profile

import com.poquiz.domain.model.User
import com.poquiz.domain.repository.ProfileRepository
import javax.inject.Inject

class NicknameUpdateUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(user:User): Boolean{
        return profileRepository.updateNickname(user)
    }
}