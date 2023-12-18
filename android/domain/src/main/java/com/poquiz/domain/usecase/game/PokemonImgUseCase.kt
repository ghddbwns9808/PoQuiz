package com.poquiz.domain.usecase.game

import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.repository.GameRepository
import javax.inject.Inject

class PokemonImgUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(id: String): PokemonImage{
        return gameRepository.loadPokemonImage(id)
    }
}