package com.poquiz.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poquiz.data.dao.RankDao
import com.poquiz.data.model.rank.HighRankEntity
import com.poquiz.data.model.rank.LowRankEntity
import com.poquiz.data.model.rank.MasterRankEntity
import com.poquiz.data.model.rank.NormalRankEntity

@Database(
    entities = [LowRankEntity::class, NormalRankEntity::class, HighRankEntity::class, MasterRankEntity::class],
    version = 1
)
abstract class PoquizDataBase: RoomDatabase() {
    abstract fun rankDao(): RankDao
}