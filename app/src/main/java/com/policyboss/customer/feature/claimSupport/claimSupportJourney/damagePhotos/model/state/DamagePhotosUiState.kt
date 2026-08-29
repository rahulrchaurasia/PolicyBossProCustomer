package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state

import android.net.Uri

data class DamagePhotosUiState(
    val photos: List<Uri> = emptyList(),
    val maxPhotos: Int = 5
)