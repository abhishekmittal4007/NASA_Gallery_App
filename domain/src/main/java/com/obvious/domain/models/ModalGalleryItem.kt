package com.obvious.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModalGalleryItem(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("hdurl") val hdurl: String?,
    @field:SerializedName("service_version") val serviceVersion: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    var isFavorite: Boolean = false
) : Parcelable

@Parcelize data class ModalGallery(var list: List<ModalGalleryItem>) : Parcelable
