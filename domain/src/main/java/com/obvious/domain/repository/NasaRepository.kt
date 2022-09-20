package com.obvious.domain.repository

import com.obvious.domain.exception.Failure
import com.obvious.domain.functional.Either
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.usecase.FetchNasaDataUseCase

interface NasaRepository {
  suspend fun getNasaData(
      params: FetchNasaDataUseCase.Params
  ): Either<Failure, List<ModalGalleryItem>>
}
