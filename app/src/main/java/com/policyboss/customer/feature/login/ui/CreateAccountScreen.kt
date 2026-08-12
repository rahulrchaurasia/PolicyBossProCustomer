package com.policyboss.customer.feature.login.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountEvent
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountUiState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.login.viewmodel.CreateAccountViewModel
import com.policyboss.customer.ui.ObserveAsEvents
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

/*
Box
 ├── HeaderPattern()
 ├── WhiteCard()
 │     ├── Fixed Floating Icon
 │     └── Scrollable Form Content
 */



/*
Box (fillMaxSize)
 ├── HeaderPattern() - Fixed, extends behind white card
 ├── WhiteCard() - Bottom positioned, contains floating icon + scrollable content
 │     ├── Floating Icon (negative offset for overlap)
 │     └── Scrollable Form Content
 */


@Composable
fun CreateAccountScreen(

    onLoginClick: () -> Unit,

    onNavigateToVerifyOtp: (String, String, VerifyOtpSource) -> Unit,

    viewModel: CreateAccountViewModel =
        hiltViewModel()
) {

    val uiState =
        viewModel.uiState
            .collectAsStateWithLifecycle()

    // =========================================================
    // EVENTS
    // =========================================================

    ObserveAsEvents(
        flow = viewModel.event
    ) { event ->

        when (event) {

            is CreateAccountEvent.NavigateToVerifyOtp -> {

                onNavigateToVerifyOtp(
                    event.fullName,
                    event.mobileNumber,
                    event.source
                )
            }

            is CreateAccountEvent.ShowSnackbar -> {

                // snackbar
            }
        }
    }

    // =========================================================
    // CONTENT
    // =========================================================

    CreateAccountContent(

        uiState = uiState.value,

        onNameChange = viewModel::onNameChange,

        onMobileChange = viewModel::onMobileChange,

        onSendOtpClick = viewModel::onSendOtpClick,

        onLoginClick = onLoginClick,

        onEmailChange = viewModel::onEmailChange,
    )
}




@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun CreateAccountScreenPreview() {

    PolicyBossCustomerTheme {

        CreateAccountContent(

            uiState = CreateAccountUiState(

                fullName = "Rahul Chaurasia",

                mobileNumber = "9876543210"
            ),

            onNameChange = {},

            onMobileChange = {},

            onSendOtpClick = {},

            onLoginClick = {},
            onEmailChange = { }
        )
    }
}