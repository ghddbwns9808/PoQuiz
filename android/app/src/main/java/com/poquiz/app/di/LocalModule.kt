package com.poquiz.app.di

import android.content.Context
import androidx.room.Room
import com.poquiz.data.PoquizDataBase
import com.poquiz.data.dao.RankDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun providesPoquizDatabase(
        @ApplicationContext context: Context
    ): PoquizDataBase {
        return Room.databaseBuilder(
            context,
            PoquizDataBase::class.java,
            "poquiz_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRankDao(
        poquizDataBase: PoquizDataBase
    ): RankDao {
        return poquizDataBase.rankDao()
    }

}