package com.poquiz.data.api

import com.poquiz.data.model.PokeInfoResponse
import com.poquiz.data.model.rank.RankRequest
import com.poquiz.data.model.rank.RankResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("/pokemons")
    suspend fun loadPokemonInfo(
        @Query("id") id: String
    ): PokeInfoResponse

    @POST("rank-low")
    suspend fun registerLowRank(
        @Body rankRequest: RankRequest
    ): Boolean

    @POST("rank-normal")
    suspend fun registerNormalRank(
        @Body rankRequest: RankRequest
    ): Boolean

    @POST("rank-high")
    suspend fun registerHighRank(
        @Body rankRequest: RankRequest
    ): Boolean

    @POST("rank-higest")
    suspend fun registerHighestRank(
        @Body rankRequest: RankRequest
    ): Boolean

    @GET("rank-low/all")
    suspend fun getLowRank(): List<RankResponse>

    @GET("rank-normal/all")
    suspend fun getNormalRank(): List<RankResponse>

    @GET("rank-high/all")
    suspend fun getHighRank(): List<RankResponse>

    @GET("rank-higest/all")
    suspend fun getHighestRank(): List<RankResponse>


}