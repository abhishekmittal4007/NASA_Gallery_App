package com.obvious.data.datastore

import com.obvious.domain.exception.Failure
import com.obvious.domain.functional.Either
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.usecase.FetchNasaDataUseCase

interface NasaDataStore {
  suspend fun fetchNasaData(
      params: FetchNasaDataUseCase.Params
  ): Either<Failure, List<ModalGalleryItem>>
}
