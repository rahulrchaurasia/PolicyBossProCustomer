package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state

sealed class CashlessGarageUiEvent {
    //object NavigateNext : CashlessGarageUiEvent()

    data class NavigateNext(val title: String, val url: String) : CashlessGarageUiEvent()
    data class ShowError(val message: String) : CashlessGarageUiEvent()
}