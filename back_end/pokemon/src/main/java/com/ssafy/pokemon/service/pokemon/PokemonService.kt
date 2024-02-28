package com.ssafy.pokemon.service.pokemon

import com.ssafy.pokemon.dto.Pokemon

interface PokemonService {
    /** 번호에 따른 포켓몬 객체 반환  */
    fun getPokemon(num: String): Pokemon
}
