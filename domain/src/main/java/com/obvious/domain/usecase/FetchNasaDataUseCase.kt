package com.obvious.domain.usecase

import com.obvious.domain.exception.Failure
import com.obvious.domain.functional.Either
import com.obvious.domain.interactor.UseCase
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.repository.NasaRepository
import javax.inject.Inject

class FetchNasaDataUseCase @Inject constructor(private val nasaRepository: NasaRepository) :
    UseCase<List<ModalGalleryItem>, FetchNasaDataUseCase.Params>() {

  override suspend fun run(params: Params): Either<Failure, List<ModalGalleryItem>> {
    return nasaRepository.getNasaData(params)
  }

  data class Params(val count: Int)
}
