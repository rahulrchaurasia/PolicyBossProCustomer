package com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state

// 2. Actions (User interactions from the Screen to the ViewModel)
sealed class ThirdPartyDetailsAction {
    data class OnDriverNameChanged(val name: String) : ThirdPartyDetailsAction()
    data class OnPhoneNumberChanged(val number: String) : ThirdPartyDetailsAction()
    object OnContinueClick : ThirdPartyDetailsAction()
}