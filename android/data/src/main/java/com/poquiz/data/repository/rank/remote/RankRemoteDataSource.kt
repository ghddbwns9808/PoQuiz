package com.poquiz.data.repository.rank.remote

import com.poquiz.domain.model.Rank

interface RankRemoteDataSource {
    suspend fun registerLowRank(rank: Rank): Boolean

    suspend fun registerNormalRank(rank: Rank): Boolean

    suspend fun registerHighRank(rank: Rank): Boolean

    suspend fun registerHighestRank(rank: Rank): Boolean

    suspend fun getLowRank(): List<Rank>

    suspend fun getNormalRank(): List<Rank>

    suspend fun getHighRank(): List<Rank>

    suspend fun getHighestRank(): List<Rank>
}