package com.poquiz.data.api

import com.poquiz.data.model.PokeImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiInterface {
    @GET("pokemon/{id}")
    suspend fun loadPokemonImage(
        @Path("id") id: String
    ): PokeImageResponse
}