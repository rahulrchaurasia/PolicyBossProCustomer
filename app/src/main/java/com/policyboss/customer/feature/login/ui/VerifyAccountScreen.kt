
package com.policyboss.customer.feature.login.ui




import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.AppTypography


import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.login.component.verifyAccount.OTPFieldState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpEvent
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.login.viewmodel.VerifyOtpViewModel
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.ui.ObserveAsEvents
import com.policyboss.customer.ui.components.loading.CustomAppLoader
import com.policyboss.customer.ui.theme.LightColorScheme
import com.policyboss.customer.utils.AppFormatter

//*********************************************************************************
//** So pass AppNavigator to Screen is recommended way.But its content view we have to used callback  *************************/
//*********************************************************************************
/*
    THE REAL INDUSTRY STANDARD TODAY
    LEVEL 1 — PURE UI COMPONENTS

    Use callbacks only.

    Example:

    LoginContent(
        onRegisterClick = {}
    )

    Correct.

    Because this UI should:

    preview easily
    test easily
    be reusable
    LEVEL 2 — SCREEN COMPOSABLES

    This is where industry differs.

    Two common patterns exist:

    Pattern	Common In
    Pass navigator	Medium/Large apps
    Pass grouped actions	Google samples / strict clean architecture

    Both are valid.

    THE IMPORTANT DISTINCTION
    THIS IS BAD
    OTPTextField(
        navigator = navigator
    )

    UI component navigation aware ❌

    THIS IS ACCEPTABLE
    VerifyAccountScreen(
        navigator = navigator
    )

    because Screen is already:

    navigation-aware
    ViewModel-aware
    lifecycle-aware

    This is NOT reusable UI anymore.This is orchestration layer.

    Big difference. YOUR CURRENT STRUCTURE
    You already separated:VerifyAccountScreen  VerifyAccountContent

    This is excellent.

    Now: Content  Pure UI.
    Screen  Coordinator/orchestrator.

    That is already clean architecture.

    SO IS PASSING AppNavigator TO SCREEN WRONG?
    NO

    This is the most misunderstood Compose topic.

    Passing navigator to SCREEN level is completely acceptable in many production apps.
 */

@Composable
fun VerifyAccountScreen(
    navigator: AppNavigator,
    fullName: String,
    mobileNumber: String,
    source : VerifyOtpSource,
    viewModel: VerifyOtpViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // =====================================
    // OBSERVE EVENTS
    // =====================================

    ObserveAsEvents(

        flow = viewModel.event
    ){event ->

        when(event){
            is VerifyOtpEvent.NavigateToLogin -> {

                navigator.navigateToLoginAndClear()
            }

            is VerifyOtpEvent.NavigateToHome -> {

                navigator.navigateToMainAndClear()
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // =====================================
        // MAIN CONTENT
        // =====================================

        VerifyAccountContent(

            mobileNumber = AppFormatter.maskMobile(mobileNumber),

            otpText = uiState.otp,

            otpLength = 4,

            fieldState = uiState.fieldState,

            errorMessage = uiState.errorMessage,

            resendMessage = uiState.resendMessage,

            resendSeconds = uiState.resendSeconds,

            isVerifyingOtp = uiState.isVerifyingOtp,

            isResendingOtp = uiState.isResendingOtp,

            onOtpChange = { value, isComplete ->

                viewModel.onOtpChange(
                    otp = value,

                    isComplete = isComplete,

                    source = source,

                    mobile =  mobileNumber
                )
            },

            onResendClick = {
                viewModel.resendOtp()
            }
        )

        // =====================================
        // GLOBAL FULLSCREEN LOADER
        // =====================================

        if (uiState.isVerifyingOtp) {

            CustomAppLoader(
                showBackground = true
            )
        }
    }
}



@Preview(
    name = "Verify Account Screen - Success",
    showBackground = true,
    showSystemUi = true
)

@Preview(
    name = "Verify Account Screen - Error",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun VerifyAccountScreenPreview_Error() {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography
    ) {

        VerifyAccountContent(

            mobileNumber = "+91 70450 *****",

            otpText = "1234",

            otpLength = 4,

            fieldState = OTPFieldState.ERROR,

            errorMessage = "Invalid code",

            resendMessage = null,

            resendSeconds = 52,

            isVerifyingOtp = false,

            isResendingOtp = false,

            onOtpChange = { _, _ -> },

            onResendClick = {}
        )
    }
}