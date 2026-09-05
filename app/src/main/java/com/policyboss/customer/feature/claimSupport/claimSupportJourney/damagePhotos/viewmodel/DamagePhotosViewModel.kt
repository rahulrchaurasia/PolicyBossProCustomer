package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DamagePhotosViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DamagePhotosUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<DamagePhotosUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: DamagePhotosAction) {
        when (action) {
            is DamagePhotosAction.OnPhotosSelected -> addPhotos(action.photos)
            is DamagePhotosAction.OnPhotoRemoved -> removePhoto(action.photo)
            DamagePhotosAction.OnUploadClick -> {
                // Handled in the Route (UI Layer)
            }
            DamagePhotosAction.OnContinueClick -> onContinueClick()
        }
    }

    /* ****************************************************
    3. Adding Photos Logic (addPhotos)
    When the UI hands this function a list of newly selected photos, it follows these exact steps:

    Combine: It takes the photos the user already had (currentPhotos) and combines them with the new photos.

    Remove Duplicates: It runs .distinctBy { it.toString() } to ensure that if the user accidentally picked the exact same image twice, it only keeps one copy.

    Enforce the Limit: It checks if the new total size is greater than your maxPhotos limit.

    Final Decision: If it is over the limit, it stops and shouts an error through the _uiEvent megaphone. If it is valid, it saves the updated list into the _uiState.
   ****************************************************.   */
    private fun addPhotos(newPhotos: List<Uri>) {
        val currentPhotos = _uiState.value.photos
        val updatedPhotos = (currentPhotos + newPhotos).distinctBy { it.toString() }

        if (updatedPhotos.size > _uiState.value.maxPhotos) {
            viewModelScope.launch { 
                _uiEvent.emit(DamagePhotosUiEvent.ShowError("Maximum ${_uiState.value.maxPhotos} photos allowed")) 
            }
            return
        }
        _uiState.update { it.copy(photos = updatedPhotos) }
    }

    //
//    4. Removing Photos Logic (removePhoto)
//    When the user clicks the "Close" or "Delete" button on a specific photo:
//
//    It grabs the current list of photos.
//
//    It uses .filterNot { it == photo } to generate a brand new list that includes everything except the specific photo they asked to delete.
//
//    It saves this newly filtered list back into the _uiState.

    private fun removePhoto(photo: Uri) {
        _uiState.update { state ->
            state.copy(photos = state.photos.filterNot { it == photo })
        }
    }

    /*
    When the user clicks the "Confirm and Continue" button:

  Validate: It checks if the photo list is completely empty. If they haven't uploaded anything, it blocks them and fires an error event ("Please add at least one damage photo").

   Navigate: If the list has at least one photo, the ViewModel packages those validated photos into the NavigateNext(currentPhotos) event and fires it. The UI catches this event and passes the data up to your parent graph.
     */

    private fun onContinueClick() {
        val currentPhotos = _uiState.value.photos
        
        if (currentPhotos.isEmpty()) {

//            viewModelScope.launch {
//                _uiEvent.emit(DamagePhotosUiEvent.ShowError("Please add at least one damage photo."))
//            }
            // 🚀 SHOW INLINE ERROR for empty validation
            _uiState.update { it.copy(errorMessage = "Please add at least one damage photo.") }
            return
        }

        // 🚀 Emit the event WITH the validated data payload
        viewModelScope.launch {
            _uiEvent.emit(DamagePhotosUiEvent.NavigateNext(currentPhotos))
        }
    }
}