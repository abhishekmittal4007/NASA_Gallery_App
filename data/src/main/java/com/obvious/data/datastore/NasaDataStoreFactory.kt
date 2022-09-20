package com.obvious.data.datastore

import com.obvious.data.api.Api
import com.obvious.domain.exception.Failure
import com.obvious.domain.functional.Either
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.usecase.FetchNasaDataUseCase
import com.obvious.remote.network.NetworkResponse
import javax.inject.Inject

class NasaDataStoreFactory
@Inject
constructor(
    private val api: Api,
) : NasaDataStore {

  override suspend fun fetchNasaData(
      params: FetchNasaDataUseCase.Params
  ): Either<Failure, List<ModalGalleryItem>> {
    return when (val response = api.getNASAGalleryList("DEMO_KEY", params.count)) {
      is NetworkResponse.Success -> {
        Either.Right(response.body)
      }
      is NetworkResponse.NetworkError -> {
        Either.Left(Failure.NetworkConnection)
      }
      is NetworkResponse.ApiError -> {
        Either.Left(Failure.ServerError)
      }
      else -> {
        Either.Left(object : Failure.FeatureFailure() {})
      }
    }
  }
}
