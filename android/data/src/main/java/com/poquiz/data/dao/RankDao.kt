package com.poquiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.poquiz.data.model.rank.HighRankEntity
import com.poquiz.data.model.rank.MasterRankEntity
import com.poquiz.data.model.rank.NormalRankEntity
import com.poquiz.data.model.rank.LowRankEntity

@Dao
interface RankDao {
    @Insert
    suspend fun insertLowRank(
        lowRankEntity: LowRankEntity
    )

    @Insert
    suspend fun insertNormalRank(
        normalRankEntity: NormalRankEntity
    )

    @Insert
    suspend fun insertHighRank(
        highRankEntity: HighRankEntity
    )

    @Insert
    suspend fun insertMasterRank(
        masterRankEntity: MasterRankEntity
    )

    @Query("SELECT * FROM low_rank ORDER BY score DESC LIMIT 10")
    suspend fun getLowRank(): List<LowRankEntity>

    @Query("SELECT * FROM normal_rank ORDER BY score DESC LIMIT 10")
    suspend fun getNormalRank(): List<NormalRankEntity>

    @Query("SELECT * FROM high_rank ORDER BY score DESC LIMIT 10")
    suspend fun getHighRank(): List<HighRankEntity>

    @Query("SELECT * FROM master_rank ORDER BY score DESC LIMIT 10")
    suspend fun getMasterRank(): List<MasterRankEntity>
}