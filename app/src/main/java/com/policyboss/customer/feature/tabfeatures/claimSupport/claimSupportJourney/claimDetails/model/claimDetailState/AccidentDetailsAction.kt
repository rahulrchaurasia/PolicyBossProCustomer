package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.LookupType

sealed class AccidentDetailsAction {
    data class OnLookupTypeChanged(val type: LookupType) : AccidentDetailsAction()
    data class OnLookupValueChanged(val value: String) : AccidentDetailsAction()
    data class OnDateChanged(val date: String) : AccidentDetailsAction()
    data class OnTimeChanged(val time: String) : AccidentDetailsAction()

    data class OnDescriptionChanged(val desc: String) : AccidentDetailsAction()

    object OnContinueClick : AccidentDetailsAction()

    data class OnLocationChanged(val location: String) : AccidentDetailsAction()

    data class OnCurrentLocationFetched(val address: String, val lat: Double, val lng: Double) : AccidentDetailsAction()
    object OnUseCurrentLocationClick : AccidentDetailsAction() // Used to trigger permission in Route

    // 🚀 Actions for toggling and clearing location
    object OnSwitchToManualLocation : AccidentDetailsAction()
    object OnClearLocation : AccidentDetailsAction()

}