package com.poquiz.domain.repository

import com.poquiz.domain.model.Rank

interface RankRepository {
    suspend fun insertLowRank(rank: Rank): Long

    suspend fun insertNormalRank(rank: Rank): Long

    suspend fun insertHighRank(rank: Rank): Long

    suspend fun insertMasterRank(rank: Rank): Long

    suspend fun getLowRank(): List<Rank>
    suspend fun getNormalRank(): List<Rank>
    suspend fun getHighRank(): List<Rank>
    suspend fun getMasterRank(): List<Rank>
}