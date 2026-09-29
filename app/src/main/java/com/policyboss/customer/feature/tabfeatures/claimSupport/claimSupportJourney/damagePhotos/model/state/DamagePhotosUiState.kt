package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.model.state

import android.net.Uri

data class DamagePhotosUiState(
    val photos: List<Uri> = emptyList(),
    val maxPhotos: Int = 5,
    val errorMessage: String? = null // 🚀 ADDED: To hold the inline error
)