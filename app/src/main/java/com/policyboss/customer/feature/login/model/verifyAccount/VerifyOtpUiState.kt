package com.policyboss.customer.feature.login.model.verifyAccount

import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState


data class VerifyOtpUiState(

    val otp: String = "",

    val fieldState: OTPFieldState =
        OTPFieldState.DEFAULT,

    val errorMessage: String? = null,

    val resendMessage: String? = null,

    val resendSeconds: Int = 120,

    val isVerifyingOtp: Boolean = false,

    val isResendingOtp: Boolean = false,

    val isOtpVerified: Boolean = false
)