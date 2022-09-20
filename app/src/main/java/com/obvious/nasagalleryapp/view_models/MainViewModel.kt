package com.obvious.nasagalleryapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obvious.domain.functional.onFailure
import com.obvious.domain.functional.onSuccess
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.domain.usecase.FetchNasaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchNasaDataUseCase: FetchNasaDataUseCase) :
    ViewModel() {

  var list: MutableLiveData<List<ModalGalleryItem>> = MutableLiveData()

  fun fetchGalleryList(count: Int) {
    fetchNasaDataUseCase(FetchNasaDataUseCase.Params(count)) {
      it.onSuccess { list.value = it }
      it.onFailure {}
    }
  }
}
