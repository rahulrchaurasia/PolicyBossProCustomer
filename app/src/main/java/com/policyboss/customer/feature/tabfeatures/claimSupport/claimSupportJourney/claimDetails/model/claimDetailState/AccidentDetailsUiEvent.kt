package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

sealed class AccidentDetailsUiEvent {
    object NavigateNext : AccidentDetailsUiEvent()
    data class ShowError(val message: String) : AccidentDetailsUiEvent()



}