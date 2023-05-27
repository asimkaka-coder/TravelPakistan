package com.tp.travelpakistan.ui.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.details.data.TourDetailsRepository
import com.tp.travelpakistan.ui.details.model.DetailImageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: TourDetailsRepository
) : ViewModel() {

    private val _detailsUiState =
        MutableStateFlow(DetailsUiState(isLoadingData = false, isError = false))
    val detailsUiState = _detailsUiState


    var imagesList = mutableStateOf(
        mutableListOf<DetailImageItem>(
        )
    )

    val selectedImage = mutableStateOf<DetailImageItem?>(null)


    fun onImageSelected(selectedItem: DetailImageItem) {
        val oldList = imagesList.value
        val newList = oldList.map NavMap@{ imageItem ->
            if (imageItem.id == selectedItem.id) {
                return@NavMap imageItem.copy(isSelected = true)
            } else {
                return@NavMap imageItem.copy(isSelected = false)
            }
        }
        imagesList.value = newList.toMutableList()
        selectedImage.value = selectedItem
    }


    fun fetchTourDetails(tourId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _detailsUiState.update {
                it.copy(isLoadingData = true)
            }
            val response = detailsRepository.getTourDetails(tourId)
            when (response) {
                is Resource.Error -> {
                    _detailsUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = true
                        )
                    }
                    Log.d("jeje", "Error: ${response.message}")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d(
                        "jeje",
                        "Message: ${response.data?.message}\n Data: ${response.data?.tour}"
                    )

                    _detailsUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = false,
                            data = response.data?.tour
                        )
                    }

                    response.data?.tour?.let {
                        it.images.forEachIndexed { index, s ->
                            imagesList.value.add(DetailImageItem(index.toString(),s,index==0))
                        }
                        selectedImage.value = imagesList.value.first { it.isSelected }
                    }

                }
            }
        }
    }
}