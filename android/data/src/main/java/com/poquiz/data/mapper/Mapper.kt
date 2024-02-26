package com.poquiz.data.mapper

import com.poquiz.data.model.PokeImageResponse
import com.poquiz.data.model.PokeInfoResponse
import com.poquiz.data.model.rank.HighRankEntity
import com.poquiz.data.model.rank.MasterRankEntity
import com.poquiz.data.model.rank.NormalRankEntity
import com.poquiz.data.model.rank.LowRankEntity
import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.model.PokemonInfo
import com.poquiz.domain.model.Rank

fun mapperToPokemonImg(pokeImageResponse: PokeImageResponse): PokemonImage{
    return PokemonImage(pokeImageResponse.sprites.front_default)
}

fun mapperToPokemonInfo(pokeInfoResponse: PokeInfoResponse): PokemonInfo{
    return PokemonInfo(pokeInfoResponse.pokemonNumber, pokeInfoResponse.pokemonName, pokeInfoResponse.pokemonType)
}

fun Rank.toLowRankEntity(): LowRankEntity{
    return LowRankEntity(
        this.nickname,
        this.score.toInt(),
        this.date
    )
}

fun Rank.toNormalRankEntity(): NormalRankEntity{
    return NormalRankEntity(
        this.nickname,
        this.score.toInt(),
        this.date
    )
}

fun Rank.toHighRankEntity(): HighRankEntity{
    return HighRankEntity(
        this.nickname,
        this.score.toInt(),
        this.date
    )
}

fun Rank.toMasterRankEntity(): MasterRankEntity{
    return MasterRankEntity(
        this.nickname,
        this.score.toInt(),
        this.date
    )
}

fun LowRankEntity.toRank(): Rank{
    return Rank(
        this.nickname,
        this.score.toString(),
        this.date
    )
}

fun NormalRankEntity.toRank(): Rank{
    return Rank(
        this.nickname,
        this.score.toString(),
        this.date
    )
}

fun HighRankEntity.toRank(): Rank{
    return Rank(
        this.nickname,
        this.score.toString(),
        this.date
    )
}

fun MasterRankEntity.toRank(): Rank{
    return Rank(
        this.nickname,
        this.score.toString(),
        this.date
    )
}