package com.poquiz.app.di

import com.poquiz.data.repository.game.GameRepositoryImpl
import com.poquiz.data.repository.game.remote.GameRemoteDataSource
import com.poquiz.data.repository.game.remote.GameRemoteDataSourceImpl
import com.poquiz.data.repository.rank.RankRepositoryImpl
import com.poquiz.data.repository.rank.remote.RankRemoteDataSource
import com.poquiz.data.repository.rank.remote.RankRemoteDataSourceImpl
import com.poquiz.domain.repository.GameRepository
import com.poquiz.domain.repository.RankRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Singleton
    @Binds
    abstract fun bindGameRemoteDataSource(gameRemoteDataSourceImpl: GameRemoteDataSourceImpl): GameRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindRankRepository(rankRepositoryImpl: RankRepositoryImpl): RankRepository

    @Singleton
    @Binds
    abstract fun bindRankRemoteDataSource(rankRemoteDataSourceImpl: RankRemoteDataSourceImpl): RankRemoteDataSource

}