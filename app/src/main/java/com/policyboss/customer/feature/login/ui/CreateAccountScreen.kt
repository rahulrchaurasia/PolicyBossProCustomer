package com.policyboss.customer.feature.login.ui

import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.policyboss.customer.feature.login.viewmodel.CreateAccountViewModel


import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountEvent
import com.policyboss.customer.feature.login.model.createAccount.CreateAccountUiState
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.ui.ObserveAsEvents

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

        onNameChange =
        viewModel::onNameChange,

        onMobileChange =
        viewModel::onMobileChange,

        onSendOtpClick =
        viewModel::onSendOtpClick,

        onLoginClick =
        onLoginClick
    )
}


//
//@Composable
//fun CreateAccountScreen(
//    onLoginClick: () -> Unit = {},
//    onSendOtpClick: () -> Unit = {}
//) {
//
//    var fullName by remember {
//        mutableStateOf("")
//    }
//
//    var mobileNumber by remember {
//        mutableStateOf("")
//    }
//
//    val scrollState = rememberScrollState()
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.primary)
//    ) {
//
//        // ===================================================
//        // TOP BACKGROUND PATTERN
//        // ===================================================
//
//        AuthHeaderPattern()
//
//        // ===================================================
//        // MAIN WHITE CARD
//        // ===================================================
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.78f)
//                .align(Alignment.BottomCenter)
//                .background(
//                    color = MaterialTheme.colorScheme.surface,
//                    shape = RoundedCornerShape(
//                        topStart = 32.dp,
//                        topEnd = 32.dp
//                    )
//                )
//        ) {
//
//            // ===================================================
//            // SCROLLABLE CONTENT
//            // ===================================================
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .imePadding()
//                    .navigationBarsPadding()
//                    .verticalScroll(scrollState)
//                    .padding(horizontal = 24.dp)
//                    .padding(top = 56.dp)
//            ) {
//
//                // ===================================================
//                // TITLE
//                // ===================================================
//
//                Text(
//                    text = "Create an account",
//
//                    style = MaterialTheme.typography.titleLarge,
//
//                    color = MaterialTheme.colorScheme.textPrimary
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // ===================================================
//                // SUBTITLE
//                // ===================================================
//
//                Text(
//                    text = "Add following details",
//
//                    style = MaterialTheme.typography.bodyMedium,
//
//                    color = MaterialTheme.colorScheme.textSecondary
//                )
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // ===================================================
//                // FULL NAME LABEL
//                // ===================================================
//
//                Text(
//                    text = "Full name",
//
//                    style = MaterialTheme.typography.labelMedium,
//
//                    color = MaterialTheme.colorScheme.textPrimary
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // ===================================================
//                // FULL NAME FIELD
//                // ===================================================
//
//                AppOutlinedTextField(
//                    value = fullName,
//
//                    onValueChange = {
//                        fullName = it
//                    },
//
//                    placeholder = "Enter your full name"
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                // ===================================================
//                // MOBILE NUMBER LABEL
//                // ===================================================
//
//                Text(
//                    text = "Mobile number",
//
//                    style = MaterialTheme.typography.labelMedium,
//
//                    color = MaterialTheme.colorScheme.textPrimary
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // ===================================================
//                // MOBILE NUMBER FIELD
//                // ===================================================
//
//
//                AppOutlinedTextField(
//                    value = mobileNumber,
//                    onValueChange = {
//                        if (it.length <= 10) {
//                            mobileNumber = it.filter { char -> char.isDigit() }
//                        }
//                    },
//                    placeholder = "Enter your mobile number",
//                    keyboardType = KeyboardType.Number,
//                    leadingContent = {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.padding(start = 16.dp)
//                        ) {
//                            Text(
//                                text = "+91",
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color(0xFF101828)
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            // Vertial line divider
//                            Box(
//                                modifier = Modifier
//                                    .height(24.dp)
//                                    .width(1.dp)
//                                    .background(Color(0xFFD0D5DD))
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                        }
//                    }
//                )
//
//
//                // ===================================================
//                // PUSH BOTTOM CONTENT
//                // ===================================================
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                // ===================================================
//                // LOGIN SECTION
//                // ===================================================
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 40.dp),
//
//                    horizontalArrangement = Arrangement.Center,
//
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    Text(
//                        text = "Have an account?",
//
//                        style = MaterialTheme.typography.bodyMedium.copy(
//                            fontWeight = FontWeight.Medium
//                        ),
//
//                        color = MaterialTheme.colorScheme.textSecondary
//                    )
//
//                    Spacer(modifier = Modifier.width(6.dp))
//
//                    Text(
//                        text = "Log In",
//
//                        style = MaterialTheme.typography.labelMedium.copy(
//                            fontWeight = FontWeight.SemiBold
//                        ),
//
//                        color = MaterialTheme.colorScheme.secondary,
//
//                        modifier = Modifier.clickable {
//                            onLoginClick()
//                        }
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // ===================================================
//                // SEND OTP BUTTON
//                // ===================================================
//
//                PrimaryCTAButton(
//                    text = "Send OTP",
//
//                    onClick = onSendOtpClick
//                )
//
//                Spacer(
//                    modifier = Modifier
//                        .navigationBarsPadding()
//                        .height(24.dp)
//                )
//            }
//        }
//
//        // ===================================================
//        // FLOATING ICON
//        // ===================================================
//
//        AuthFloatingIcon(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            iconRes = R.drawable.ic_login
//        )
//    }
//}
//


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

            onLoginClick = {}
        )
    }
}