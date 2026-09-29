package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.Insurer
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.VehicleType

sealed class CashlessGarageAction {
    data class OnVehicleTypeSelected(val type: VehicleType) : CashlessGarageAction()
    data class OnLocationChanged(val location: String) : CashlessGarageAction()
    
    // Auto Location Actions
    object OnUseCurrentLocationClick : CashlessGarageAction() // Caught by Route
    data class OnCurrentLocationFetched(val address: String, val lat: Double, val lng: Double) : CashlessGarageAction()
    object OnClearLocation : CashlessGarageAction() // Added this

    data class OnInsurerSelected(val insurer: Insurer) : CashlessGarageAction()
    object OnFindGaragesClick : CashlessGarageAction()



   }