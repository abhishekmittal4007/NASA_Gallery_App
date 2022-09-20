package com.obvious.data.repository

import com.obvious.data.datastore.NasaDataStore
import com.obvious.domain.exception.Failure
import com.obvious.domain.functional.Either
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.repository.NasaRepository
import com.obvious.domain.usecase.FetchNasaDataUseCase
import javax.inject.Inject

class NasaDataRepository @Inject constructor(private val nasaDataStore: NasaDataStore) :
    NasaRepository {

  override suspend fun getNasaData(
      params: FetchNasaDataUseCase.Params
  ): Either<Failure, List<ModalGalleryItem>> {
    return nasaDataStore.fetchNasaData(params)
  }
}
