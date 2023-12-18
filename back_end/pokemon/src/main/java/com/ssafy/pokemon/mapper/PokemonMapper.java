package com.ssafy.pokemon.mapper;

import com.ssafy.pokemon.dto.Pokemon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PokemonMapper {
    /** 번호에 따른 포켓몬 객체 반환 **/
    Pokemon selectPokemon(String num);


}
