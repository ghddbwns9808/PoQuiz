package com.ssafy.pokemon.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pokemon {
    private int pokemonNumber;
    private String pokemonType;
    private String pokemonName;

    @Builder
    public Pokemon(int num, String type, String name){
        this.pokemonNumber = num;
        this.pokemonType = type;
        this.pokemonName = name;
    }
}
