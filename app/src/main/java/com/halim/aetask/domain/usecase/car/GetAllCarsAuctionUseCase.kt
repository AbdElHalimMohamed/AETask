package com.halim.aetask.domain.usecase.car

import com.halim.aetask.domain.entity.CarsAuction
import com.halim.aetask.domain.excutor.transformer.ThreadTransformer
import com.halim.aetask.domain.repository.CarsAuctionRepository
import io.reactivex.Single


class GetAllCarsAuctionUseCase(repo: CarsAuctionRepository,
                               threadTransformer: ThreadTransformer)
    : CarsUseCase<CarsAuction, CarsUseCase.Params.GetAllCarsParam>(repo, threadTransformer) {

    override fun buildUseCaseObservable(params: Params.GetAllCarsParam): Single<CarsAuction> =
            repo.getAuctionCars(params.refreshInterval)
}