package com.poquiz.data.mapper

import com.poquiz.data.model.PokeImageResponse
import com.poquiz.data.model.PokeInfoResponse
import com.poquiz.data.model.rank.RankRequest
import com.poquiz.data.model.rank.RankResponse
import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.model.PokemonInfo
import com.poquiz.domain.model.Rank

fun mapperToPokemonImg(pokeImageResponse: PokeImageResponse): PokemonImage{
    return PokemonImage(pokeImageResponse.sprites.front_default)
}

fun mapperToPokemonInfo(pokeInfoResponse: PokeInfoResponse): PokemonInfo{
    return PokemonInfo(pokeInfoResponse.pokemonNumber, pokeInfoResponse.pokemonName, pokeInfoResponse.pokemonType)
}

fun mapperToRankRequest(rank: Rank): RankRequest{
    return RankRequest(rank.id, rank.nickname, rank.score)
}

fun mapperToRank(rankResponse: RankResponse): Rank{
    return Rank(rankResponse.userId, rankResponse.userNickname, rankResponse.userScore)
}