package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType

sealed class AccidentDetailsAction {
    data class OnLookupTypeChanged(val type: LookupType) : AccidentDetailsAction()
    data class OnLookupValueChanged(val value: String) : AccidentDetailsAction()
    data class OnDateChanged(val date: String) : AccidentDetailsAction()
    data class OnTimeChanged(val time: String) : AccidentDetailsAction()
    data class OnLocationChanged(val location: String) : AccidentDetailsAction()
    data class OnDescriptionChanged(val desc: String) : AccidentDetailsAction()
    object OnUseCurrentLocation : AccidentDetailsAction()
    object OnContinueClick : AccidentDetailsAction()
}