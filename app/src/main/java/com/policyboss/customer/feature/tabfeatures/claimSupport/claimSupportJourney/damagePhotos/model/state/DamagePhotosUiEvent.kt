package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.model.state

import android.net.Uri

sealed interface DamagePhotosUiEvent {
    // 🚀 We pass the data directly in the event to avoid stale UI state reads
    data class NavigateNext(val photos: List<Uri>) : DamagePhotosUiEvent
    data class ShowError(val message: String) : DamagePhotosUiEvent
}