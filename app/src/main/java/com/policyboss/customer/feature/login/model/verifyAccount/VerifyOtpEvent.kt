package com.policyboss.customer.feature.login.model.verifyAccount

sealed interface VerifyOtpEvent {

    data object NavigateToHome :
        VerifyOtpEvent

    data object NavigateToLogin :
        VerifyOtpEvent

//    data class ShowSnackbar(
//        val message: String,
//        val type: SnackbarType = SnackbarType.INFO
//    ) : VerifyOtpEvent
}
