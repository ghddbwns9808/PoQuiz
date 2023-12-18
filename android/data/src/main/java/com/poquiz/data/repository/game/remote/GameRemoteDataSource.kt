package com.poquiz.data.repository.game.remote

import com.poquiz.data.model.PokeImageResponse
import com.poquiz.data.model.PokeInfoResponse

interface GameRemoteDataSource {
    suspend fun loadPokemonImage(id: String): PokeImageResponse

    suspend fun loadPokemonInfo(id: String): PokeInfoResponse
}