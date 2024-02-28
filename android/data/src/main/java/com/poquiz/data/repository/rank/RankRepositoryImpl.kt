package com.poquiz.data.repository.rank

import com.poquiz.data.repository.rank.local.RankLocalDataSource
import com.poquiz.domain.model.Rank
import com.poquiz.domain.repository.RankRepository
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val rankLocalDataSource: RankLocalDataSource
) : RankRepository{
    override suspend fun insertLowRank(rank: Rank): Long {
        return rankLocalDataSource.insertLowRank(rank)
    }

    override suspend fun insertNormalRank(rank: Rank): Long {
        return rankLocalDataSource.insertNormalRank(rank)
    }

    override suspend fun insertHighRank(rank: Rank): Long {
        return rankLocalDataSource.insertHighRank(rank)
    }

    override suspend fun insertMasterRank(rank: Rank) : Long{
        return rankLocalDataSource.insertMasterRank(rank)
    }

    override suspend fun getLowRank(): List<Rank> {
        return fillTenRank(rankLocalDataSource.getLowRank())
    }

    override suspend fun getNormalRank(): List<Rank> {
        return fillTenRank(rankLocalDataSource.getNormalRank())
    }

    override suspend fun getHighRank(): List<Rank> {
        return fillTenRank(rankLocalDataSource.getHighRank())
    }

    override suspend fun getMasterRank(): List<Rank> {
        return fillTenRank(rankLocalDataSource.getMasterRank())
    }

    private fun fillTenRank(result: List<Rank>): List<Rank>{
        val res = result as MutableList<Rank>
        while (result.size < 10){
            result.add(
                Rank("", "_ _ _", 0)
            )
        }
        return res
    }

}