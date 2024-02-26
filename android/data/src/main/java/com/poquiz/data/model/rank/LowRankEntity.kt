package com.poquiz.data.model.rank

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "low_rank")
data class LowRankEntity(
    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "date")
    val date: Long
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
