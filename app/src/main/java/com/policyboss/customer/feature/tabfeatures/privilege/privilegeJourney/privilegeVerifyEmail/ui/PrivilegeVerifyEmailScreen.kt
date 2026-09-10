package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpUiState
import com.policyboss.customer.ui.components.otpVerify.OTPVerifyAccountContent


@Composable
fun PrivilegeVerifyEmailScreen(
    uiState: VerifyOtpUiState,
    mobileNumber: String,
    onOtpChange: (String, Boolean) -> Unit,
    onResendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OTPVerifyAccountContent(
        headerIcon = R.drawable.ic_login, // Replace with appropriate icon if needed
        title = "Verify Privilege",
        subtitle = "We’ve sent a verification code to your mobile number",
        targetValue = mobileNumber,
        mobileNumber = mobileNumber,
        otpText = uiState.otp,
        otpLength = 4,
        fieldState = uiState.fieldState,
        errorMessage = uiState.errorMessage,
        resendMessage = uiState.resendMessage,
        resendSeconds = uiState.resendSeconds,
        isVerifyingOtp = uiState.isVerifyingOtp,
        isResendingOtp = uiState.isResendingOtp,
        onOtpChange = onOtpChange,
        onResendClick = onResendClick,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true, name = "1. Default")
@Composable
fun PrivilegeVerifyEmailScreenPreviewDefault() {
    // PolicyBossTheme { // Optional: Wrap in your app's theme
    PrivilegeVerifyEmailScreen(
        uiState = VerifyOtpUiState(
            otp = "12",
            fieldState = OTPFieldState.DEFAULT,
            resendSeconds = 45
        ),
        mobileNumber = "+91 9876543210",
        onOtpChange = { _, _ -> },
        onResendClick = {}
    )
    // }
}

@Preview(showBackground = true, showSystemUi = true, name = "2. Error State")
@Composable
fun PrivilegeVerifyEmailScreenPreviewError() {
    PrivilegeVerifyEmailScreen(
        uiState = VerifyOtpUiState(
            otp = "1234",
            fieldState = OTPFieldState.ERROR,
            errorMessage = "Invalid OTP entered. Please try again.",
            resendSeconds = 20
        ),
        mobileNumber = "+91 9876543210",
        onOtpChange = { _, _ -> },
        onResendClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true, name = "3. Resend Available")
@Composable
fun PrivilegeVerifyEmailScreenPreviewResendAvailable() {
    PrivilegeVerifyEmailScreen(
        uiState = VerifyOtpUiState(
            otp = "",
            fieldState = OTPFieldState.DEFAULT,
            resendSeconds = 0,
            isResendingOtp = false
        ),
        mobileNumber = "+91 9876543210",
        onOtpChange = { _, _ -> },
        onResendClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true, name = "4. Success State")
@Composable
fun PrivilegeVerifyEmailScreenPreviewSuccess() {
    PrivilegeVerifyEmailScreen(
        uiState = VerifyOtpUiState(
            otp = "9876",
            fieldState = OTPFieldState.SUCCESS,
            resendSeconds = 110,
            isOtpVerified = true
        ),
        mobileNumber = "+91 9876543210",
        onOtpChange = { _, _ -> },
        onResendClick = {}
    )
}