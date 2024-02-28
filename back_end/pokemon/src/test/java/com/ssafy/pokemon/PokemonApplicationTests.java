package com.ssafy.pokemon;

import com.ssafy.pokemon.Controller.pokemon.PokemonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration
@SpringBootTest
class PokemonApplicationTests {

	@Autowired
	PokemonController controller;

	@Test
	void contextLoads() {
	}
	@Test
	void pokeInfoApiTest(){
		assertThat(controller.select("1").getPokemonName().trim()).isEqualTo("이상해씨");
	}

}
