package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state

sealed class InsurerContactListEvent {


    data class NavigateToWebView(val title: String, val url: String) : InsurerContactListEvent()
}