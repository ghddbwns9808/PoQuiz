package com.poquiz.app.di

import com.poquiz.data.repository.game.GameRepositoryImpl
import com.poquiz.data.repository.game.remote.GameRemoteDataSource
import com.poquiz.data.repository.game.remote.GameRemoteDataSourceImpl
import com.poquiz.data.repository.login.LoginRepositoryImpl
import com.poquiz.data.repository.login.remote.LoginRemoteDataSource
import com.poquiz.data.repository.login.remote.LoginRemoteDataSourceImpl
import com.poquiz.data.repository.profile.ProfileRepositoryImpl
import com.poquiz.data.repository.profile.remote.ProfileRemoteDataSource
import com.poquiz.data.repository.profile.remote.ProfileRemoteDataSourceImpl
import com.poquiz.data.repository.rank.RankRepositoryImpl
import com.poquiz.data.repository.rank.remote.RankRemoteDataSource
import com.poquiz.data.repository.rank.remote.RankRemoteDataSourceImpl
import com.poquiz.domain.model.Rank
import com.poquiz.domain.repository.GameRepository
import com.poquiz.domain.repository.LoginRepository
import com.poquiz.domain.repository.ProfileRepository
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
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    abstract fun bindLoginRemoteDataSource(loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl): LoginRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Singleton
    @Binds
    abstract fun bindProfileRemoteDataSource(profileRemoteDataSourceImpl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

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