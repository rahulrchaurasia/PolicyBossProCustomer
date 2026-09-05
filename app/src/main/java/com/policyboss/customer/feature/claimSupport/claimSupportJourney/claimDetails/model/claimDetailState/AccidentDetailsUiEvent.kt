package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

sealed class AccidentDetailsUiEvent {
    object NavigateNext : AccidentDetailsUiEvent()
    data class ShowError(val message: String) : AccidentDetailsUiEvent()



}