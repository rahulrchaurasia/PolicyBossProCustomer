package com.policyboss.customer.feature.login.model.createAccount

import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource

sealed interface CreateAccountEvent {

    data class NavigateToVerifyOtp(
        val fullName: String,
        val mobileNumber: String,
        val source: VerifyOtpSource
    ) : CreateAccountEvent

    data class ShowSnackbar(
        val message: String
    ) : CreateAccountEvent
}