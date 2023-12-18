package com.poquiz.data.repository.rank.remote

import com.poquiz.data.api.ApiInterface
import com.poquiz.data.api.springApi
import com.poquiz.data.mapper.mapperToRank
import com.poquiz.data.mapper.mapperToRankRequest
import com.poquiz.domain.model.Rank
import javax.inject.Inject

class RankRemoteDataSourceImpl @Inject constructor(
    @springApi private val api: ApiInterface
): RankRemoteDataSource {

    override suspend fun registerLowRank(rank: Rank): Boolean {
        return api.registerLowRank(mapperToRankRequest(rank))
    }

    override suspend fun registerNormalRank(rank: Rank): Boolean {
        return api.registerNormalRank(mapperToRankRequest(rank))
    }

    override suspend fun registerHighRank(rank: Rank): Boolean {
        return api.registerHighRank(mapperToRankRequest(rank))
    }

    override suspend fun registerHighestRank(rank: Rank): Boolean {
        return api.registerHighestRank(mapperToRankRequest(rank))
    }

    override suspend fun getLowRank(): List<Rank> {
        return api.getLowRank().map { mapperToRank(it) }
    }

    override suspend fun getNormalRank(): List<Rank> {
        return api.getNormalRank().map { mapperToRank(it) }
    }

    override suspend fun getHighRank(): List<Rank> {
        return api.getHighRank().map { mapperToRank(it) }
    }

    override suspend fun getHighestRank(): List<Rank> {
        return api.getHighestRank().map { mapperToRank(it) }
    }
}