package com.policyboss.customer.feature.claimSupport.model.claimSupportState
sealed interface ClaimSupportAction {
    object firstClick : ClaimSupportAction
    object secondClick : ClaimSupportAction
    // ...
}