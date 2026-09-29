package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state

sealed class InsurerContactListAction {
    data class OnSearchQueryChanged(val query: String) : InsurerContactListAction()
    object OnToggleViewMode : InsurerContactListAction()
    data class OnInsurerClicked(val insurerId: String) : InsurerContactListAction()
}