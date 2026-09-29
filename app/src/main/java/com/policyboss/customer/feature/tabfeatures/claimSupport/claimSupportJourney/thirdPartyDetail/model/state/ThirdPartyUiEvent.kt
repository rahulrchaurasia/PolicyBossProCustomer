package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state

sealed class ThirdPartyUiEvent {
    object NavigateNext : ThirdPartyUiEvent()
    data class ShowError(val message: String) : ThirdPartyUiEvent()
}