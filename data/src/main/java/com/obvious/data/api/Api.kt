package com.obvious.data.api

import com.obvious.domain.models.ModalGalleryItem
import com.obvious.remote.EndPoints
import com.obvious.remote.network.ErrorResponse
import com.obvious.remote.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
  /**
   * GET NASA IMAGES
   * @param count is typically count of images
   * @return CommentsResponse json Object or Error Response
   */
  @GET(EndPoints.NASA_API)
  suspend fun getNASAGalleryList(
	  @Query("api_key") api_key: String,
	  @Query("count") count: Int
  ): NetworkResponse<List<ModalGalleryItem>, ErrorResponse>
}
