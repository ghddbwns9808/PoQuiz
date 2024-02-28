package com.ssafy.pokemon.service.pokemon;

import com.ssafy.pokemon.dto.Pokemon;

public interface PokemonService {
    /** 번호에 따른 포켓몬 객체 반환 **/
    Pokemon getPokemon(String num);

}
