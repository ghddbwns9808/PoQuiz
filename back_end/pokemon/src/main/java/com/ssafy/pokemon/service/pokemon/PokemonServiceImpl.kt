package com.ssafy.pokemon.service.pokemon

import com.ssafy.pokemon.dto.Pokemon
import com.ssafy.pokemon.mapper.PokemonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PokemonServiceImpl : PokemonService {
    @Autowired
    private lateinit var pm: PokemonMapper

    override fun getPokemon(num: String): Pokemon {
        return pm.selectPokemon(num)
    }
}
