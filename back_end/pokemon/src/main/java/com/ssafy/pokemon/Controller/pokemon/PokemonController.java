package com.ssafy.pokemon.Controller.pokemon;

import com.ssafy.pokemon.dto.Pokemon;
import com.ssafy.pokemon.service.pokemon.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin("*")
@Tag(name = "Pokemon", description = "Pokemon API")
public class PokemonController {

    @Autowired
    private PokemonService ps;

    @GetMapping
    @Operation(summary = "포켓몬 조회", description = "포켓몬 번호에 맞는 정보를 반환한다.")
    public Pokemon select(String id){
        if(Integer.parseInt(id) >= 1 && Integer.parseInt(id) <= 400){
            return ps.getPokemon(id);
        }
        return new Pokemon(-1, "none", "none");
    }
}
