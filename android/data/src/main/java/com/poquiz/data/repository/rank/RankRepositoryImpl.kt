package com.poquiz.data.repository.rank

import com.poquiz.data.repository.rank.remote.RankRemoteDataSource
import com.poquiz.domain.model.Rank
import com.poquiz.domain.repository.RankRepository
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val rankRemoteDataSource: RankRemoteDataSource
) : RankRepository{
    override suspend fun registerLowRank(rank: Rank): Boolean {
        return rankRemoteDataSource.registerLowRank(rank)
    }

    override suspend fun registerMidRank(rank: Rank): Boolean {
        return rankRemoteDataSource.registerNormalRank(rank)
    }

    override suspend fun registerHighRank(rank: Rank): Boolean {
        return rankRemoteDataSource.registerHighRank(rank)
    }

    override suspend fun registerHighestRank(rank: Rank): Boolean {
        return rankRemoteDataSource.registerHighestRank(rank)
    }

    override suspend fun getLowRank(): List<Rank> {
        return fillTenRank(rankRemoteDataSource.getLowRank())
    }

    override suspend fun getNormalRank(): List<Rank> {
        return fillTenRank(rankRemoteDataSource.getNormalRank())
    }

    override suspend fun getHighRank(): List<Rank> {
        return fillTenRank(rankRemoteDataSource.getHighRank())
    }

    override suspend fun getHighestRank(): List<Rank> {
        return fillTenRank(rankRemoteDataSource.getHighestRank())
    }

    private fun fillTenRank(result: List<Rank>): List<Rank>{
        val res = result as MutableList<Rank>
        while (result.size < 10){
            result.add(
                Rank("", "_ _ _", 0)
            )
        }
        return res as List<Rank>
    }

}