package com.policyboss.customer.ui.components.otpVerify


sealed interface CommonVerifyOtpEvent {

    data object onNavigateBack :
        CommonVerifyOtpEvent

    data object onNavigateNext :
        CommonVerifyOtpEvent

//    data class ShowSnackbar(
//        val message: String,
//        val type: SnackbarType = SnackbarType.INFO
//    ) : VerifyOtpEvent
}