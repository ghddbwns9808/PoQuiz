package com.poquiz.domain.usecase.rank

import com.poquiz.domain.model.Rank
import com.poquiz.domain.repository.RankRepository
import javax.inject.Inject

class RegistLowRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
){
    suspend operator fun invoke(rank: Rank): Boolean{
        return rankRepository.registerLowRank(rank)
    }
}