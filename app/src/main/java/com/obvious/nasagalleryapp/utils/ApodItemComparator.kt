package com.obvious.nasagalleryapp.utils

import android.os.Build
import com.obvious.domain.models.ModalGalleryItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ApodItemComparator : Comparator<ModalGalleryItem> {
  override fun compare(o1: ModalGalleryItem, o2: ModalGalleryItem): Int {
    val date1 =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            LocalDate.parse(o1.date, DateTimeFormatter.ISO_DATE)
        else TODO("VERSION.SDK_INT < O")
    val date2 =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            LocalDate.parse(o2.date, DateTimeFormatter.ISO_DATE)
        else TODO("VERSION.SDK_INT < O")
    return when {
      date1.isAfter(date2) -> -1
      date2.isAfter(date1) -> 1
      else -> 0
    }
  }
}
