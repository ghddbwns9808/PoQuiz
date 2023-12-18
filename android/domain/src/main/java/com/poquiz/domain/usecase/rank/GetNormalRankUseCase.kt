package com.poquiz.domain.usecase.rank

import com.poquiz.domain.model.Rank
import com.poquiz.domain.repository.RankRepository
import javax.inject.Inject

class GetNormalRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
) {
    suspend operator fun invoke(): List<Rank>{
        return rankRepository.getNormalRank()
    }
}