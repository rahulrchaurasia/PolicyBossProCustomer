package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.model.state

import android.net.Uri

sealed interface DamagePhotosAction {
    data class OnPhotosSelected(val photos: List<Uri>) : DamagePhotosAction
    data class OnPhotoRemoved(val photo: Uri) : DamagePhotosAction
    data object OnUploadClick : DamagePhotosAction
    data object OnContinueClick : DamagePhotosAction
}