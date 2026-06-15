package com.policyboss.customer.feature.login.model.login

import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource

sealed interface LoginEvent {

    data class NavigateToVerifyOtp(

        val mobileNumber: String,

        val source: VerifyOtpSource

    ) : LoginEvent

    data class ShowSnackbar(
        val message: String
    ) : LoginEvent
}