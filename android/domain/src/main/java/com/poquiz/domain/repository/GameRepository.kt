package com.poquiz.domain.repository

import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.model.PokemonInfo

interface GameRepository {
    suspend fun loadPokemonImage(id: String): PokemonImage

    suspend fun loadPokemonInfo(id: String): PokemonInfo
}