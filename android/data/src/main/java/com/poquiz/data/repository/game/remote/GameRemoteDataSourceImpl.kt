package com.poquiz.data.repository.game.remote

import com.poquiz.data.api.ApiInterface
import com.poquiz.data.api.PokeApiInterface
import com.poquiz.data.api.pokeApi
import com.poquiz.data.api.springApi
import com.poquiz.data.model.PokeImageResponse
import com.poquiz.data.model.PokeInfoResponse
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    @springApi private val api: ApiInterface,
    @pokeApi private val pokeApi: PokeApiInterface
): GameRemoteDataSource {

    override suspend fun loadPokemonImage(id: String): PokeImageResponse {
        return pokeApi.loadPokemonImage(id)
    }

    override suspend fun loadPokemonInfo(id: String): PokeInfoResponse {
        return api.loadPokemonInfo(id)
    }
}