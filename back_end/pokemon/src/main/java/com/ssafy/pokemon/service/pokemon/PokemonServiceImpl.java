package com.ssafy.pokemon.service.pokemon;

import com.ssafy.pokemon.dto.Pokemon;
import com.ssafy.pokemon.mapper.PokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService{

    @Autowired
    private PokemonMapper pm;

    @Override
    public Pokemon getPokemon(String num) {
        return pm.selectPokemon(num);
    }
}
