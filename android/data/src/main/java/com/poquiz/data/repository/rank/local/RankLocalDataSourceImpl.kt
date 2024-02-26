package com.poquiz.data.repository.rank.local

import com.poquiz.data.dao.RankDao
import com.poquiz.data.mapper.toHighRankEntity
import com.poquiz.data.mapper.toLowRankEntity
import com.poquiz.data.mapper.toMasterRankEntity
import com.poquiz.data.mapper.toNormalRankEntity
import com.poquiz.data.mapper.toRank
import com.poquiz.domain.model.Rank
import javax.inject.Inject

class RankLocalDataSourceImpl @Inject constructor(
    private val rankDao: RankDao
): RankLocalDataSource {
    override suspend fun insertLowRank(rank: Rank) {
        rankDao.insertLowRank(rank.toLowRankEntity())
    }

    override suspend fun insertNormalRank(rank: Rank) {
        rankDao.insertNormalRank(rank.toNormalRankEntity())
    }

    override suspend fun insertHighRank(rank: Rank) {
        rankDao.insertHighRank(rank.toHighRankEntity())
    }

    override suspend fun insertMasterRank(rank: Rank) {
        rankDao.insertMasterRank(rank.toMasterRankEntity())
    }

    override suspend fun getLowRank(): List<Rank> {
        return rankDao.getLowRank().map {
            it.toRank()
        }
    }

    override suspend fun getNormalRank(): List<Rank> {
        return rankDao.getNormalRank().map {
            it.toRank()
        }
    }

    override suspend fun getHighRank(): List<Rank> {
        return rankDao.getHighRank().map {
            it.toRank()
        }
    }

    override suspend fun getMasterRank(): List<Rank> {
        return rankDao.getMasterRank().map {
            it.toRank()
        }
    }
}