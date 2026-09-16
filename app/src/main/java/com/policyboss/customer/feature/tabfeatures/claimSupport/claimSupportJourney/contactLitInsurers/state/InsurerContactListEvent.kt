package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state

sealed class InsurerContactListEvent {
    data class ShowToast(val message: String) : InsurerContactListEvent()
}