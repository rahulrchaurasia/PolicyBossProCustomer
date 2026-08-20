package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState

// 3. UI Events: One-off events triggered BY the ViewModel TO the UI (like Navigation)
sealed class FileClaimUiEvent {

    object NavigateToNextStep : FileClaimUiEvent()
}