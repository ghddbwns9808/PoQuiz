package com.ssafy.pokemon.Controller.pokemon

import com.ssafy.pokemon.dto.Pokemon
import com.ssafy.pokemon.service.pokemon.PokemonService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemons")
@CrossOrigin("*")
@Tag(name = "Pokemon", description = "Pokemon API")
class PokemonController {
    @Autowired
    private lateinit var ps: PokemonService

    @GetMapping
    @Operation(summary = "포켓몬 조회", description = "포켓몬 번호에 맞는 정보를 반환한다.")
    fun select(id: String): Pokemon {
        return if (id.toInt() in 1..400)
                    ps.getPokemon(id)
            else
                Pokemon(-1, "none", "none")
    }
}