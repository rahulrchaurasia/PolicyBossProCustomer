package com.policyboss.customer.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.AuthFloatingIcon
import com.policyboss.customer.feature.login.component.AuthHeaderPattern

import com.policyboss.customer.feature.login.model.createAccount.CreateAccountUiState
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.border
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary

@Composable
fun CreateAccountContent(

    uiState: CreateAccountUiState,

    onNameChange: (String) -> Unit,

    onMobileChange: (String) -> Unit,

    onSendOtpClick: () -> Unit,

    onLoginClick: () -> Unit
) {

    val scrollState =
        rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primary
            )
    ) {

        AuthHeaderPattern()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.78f)
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colorScheme.surface,

                    shape = androidx.compose.foundation.shape.RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .navigationBarsPadding()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp)
                    .padding(top = 56.dp)
            ) {

                Text(
                    text = "Create an account",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color =
                        MaterialTheme.colorScheme.textPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Add following details",

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        MaterialTheme.colorScheme.textSecondary
                )

                Spacer(modifier = Modifier.height(24.dp))

                // =====================================================
                // NAME
                // =====================================================

                Text(
                    text = "Full name",

                    style =
                        MaterialTheme.typography.labelMedium,

                    color =
                        MaterialTheme.colorScheme.textPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(

                    value = uiState.fullName,

                    onValueChange = onNameChange,

                    placeholder = "Enter your full name",

                    isError = uiState.isNameError,

                    errorMessage =
                        uiState.nameErrorMessage
                )

                Spacer(modifier = Modifier.height(20.dp))

                // =====================================================
                // MOBILE
                // =====================================================

                Text(
                    text = "Mobile number",

                    style =
                        MaterialTheme.typography.labelMedium,

                    color =
                        MaterialTheme.colorScheme.textPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(

                    value = uiState.mobileNumber,

                    onValueChange = onMobileChange,

                    placeholder =
                        "Enter your mobile number",

                    keyboardType =
                        KeyboardType.Number,

                    isError =
                        uiState.isMobileError,

                    errorMessage =
                        uiState.mobileErrorMessage,

                    leadingContent = {

                        Row(
                            verticalAlignment =
                                Alignment.CenterVertically,

                            modifier =
                                Modifier.padding(start = 16.dp)
                        ) {

                            Text(
                                text = "+91",

                                fontSize = 18.sp,

                                fontWeight =
                                    FontWeight.Medium,

                                color =
                                    Color(0xFF101828)
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(12.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(1.dp)
                                    .background(
                                        MaterialTheme
                                            .colorScheme
                                            .border
                                    )
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(12.dp)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                // =====================================================
                // LOGIN
                // =====================================================

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),

                    horizontalArrangement =
                        Arrangement.Center,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Have an account?",

                        style =
                            MaterialTheme.typography.bodyMedium,

                        color =
                            MaterialTheme.colorScheme.textSecondary
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Log In",

                        style =
                            MaterialTheme.typography.labelMedium,

                        color =
                            MaterialTheme.colorScheme.secondary,

                        modifier = Modifier.clickable {
                            onLoginClick()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // =====================================================
                // BUTTON
                // =====================================================

                PrimaryCTAButton(

                    text = "Send OTP",

                    onClick = onSendOtpClick
                )

                Spacer(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(24.dp)
                )
            }
        }

        AuthFloatingIcon(
            modifier =
                Modifier.align(Alignment.BottomCenter),

            iconRes = R.drawable.ic_login
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CreateAccountContentPreview() {

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