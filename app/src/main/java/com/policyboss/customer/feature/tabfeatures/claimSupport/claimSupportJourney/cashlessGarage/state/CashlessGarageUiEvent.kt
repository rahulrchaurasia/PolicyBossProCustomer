package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state

sealed class CashlessGarageUiEvent {
    object NavigateNext : CashlessGarageUiEvent()
    data class ShowError(val message: String) : CashlessGarageUiEvent()
}