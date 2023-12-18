package com.poquiz.domain.usecase.game

import com.poquiz.domain.model.PokemonInfo
import com.poquiz.domain.repository.GameRepository
import javax.inject.Inject

class PokemonInfoUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(id: String): PokemonInfo{
        return gameRepository.loadPokemonInfo(id)
    }
}