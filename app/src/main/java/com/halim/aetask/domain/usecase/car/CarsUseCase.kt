package com.halim.aetask.domain.usecase.car

import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import com.halim.aetask.domain.repository.CarsAuctionRepository
import com.halim.aetask.domain.usecase.UseCase


abstract class CarsUseCase<T, in Params : CarsUseCase.Params>(protected val repo: CarsAuctionRepository,
                                                              threadTransformer: ThreadTransformer)
    : UseCase<T, Params>(threadTransformer) {

    sealed class Params {
        data class GetAllCarsParam(val refreshInterval: Long) : Params()
    }
}