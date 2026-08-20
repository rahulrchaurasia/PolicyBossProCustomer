package com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state

sealed class ThirdPartyUiEvent {
    object NavigateNext : ThirdPartyUiEvent()
    data class ShowError(val message: String) : ThirdPartyUiEvent()
}