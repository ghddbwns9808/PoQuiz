package com.poquiz.data.repository.game

import com.poquiz.data.mapper.mapperToPokemonImg
import com.poquiz.data.mapper.mapperToPokemonInfo
import com.poquiz.data.repository.game.remote.GameRemoteDataSource
import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.model.PokemonInfo
import com.poquiz.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameRemoteDataSource: GameRemoteDataSource
): GameRepository {
    override suspend fun loadPokemonImage(id: String): PokemonImage {
        return mapperToPokemonImg(gameRemoteDataSource.loadPokemonImage(id))
    }

    override suspend fun loadPokemonInfo(id: String): PokemonInfo {
        return mapperToPokemonInfo(gameRemoteDataSource.loadPokemonInfo(id))
    }
}