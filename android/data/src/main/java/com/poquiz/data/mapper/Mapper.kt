package com.poquiz.data.mapper

import com.poquiz.data.model.PokeImageResponse
import com.poquiz.data.model.PokeInfoResponse
import com.poquiz.data.model.UserResponse
import com.poquiz.data.model.rank.RankRequest
import com.poquiz.data.model.rank.RankResponse
import com.poquiz.domain.model.PokemonImage
import com.poquiz.domain.model.PokemonInfo
import com.poquiz.domain.model.Rank
import com.poquiz.domain.model.User

fun mapperToUser(userResponse: UserResponse): User {
    return User(id = userResponse.userId, pw = userResponse.userPassword, nickname = userResponse.userNickname)
}

fun mapperToUserResponse(user: User): UserResponse{
    return UserResponse(userId = user.id, userPassword = user.pw, userNickname = user.nickname)
}

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