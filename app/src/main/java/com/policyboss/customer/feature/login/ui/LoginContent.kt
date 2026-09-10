package com.policyboss.customer.feature.login.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.AuthFloatingIcon
import com.policyboss.customer.feature.login.component.AuthHeaderPattern
import com.policyboss.customer.feature.login.model.login.LoginUiState
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary

@Composable
fun LoginContent(

    uiState: LoginUiState,

    onMobileChange: (String) -> Unit,

    onSendOtpClick: () -> Unit,

    onNavigateToRegister: () -> Unit
) {

    val scrollState =
        rememberScrollState()

    // 🚀 1. Get the keyboard controller at the top of your Composable function
    val keyboardController = LocalSoftwareKeyboardController.current

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

                    color =
                        MaterialTheme.colorScheme.surface,

                    shape = RoundedCornerShape(
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
                    text = "Login to your account",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color =
                        MaterialTheme.colorScheme.textPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Verify your mobile number",

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        MaterialTheme.colorScheme.textSecondary
                )

                Spacer(modifier = Modifier.height(32.dp))

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

                    // 🚀 1. Set ImeAction to Done
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),

                    // 🚀 2. Handle the Done click to hide keyboard and/or trigger OTP
                    keyboardActions = KeyboardActions(
                        onDone = {

                            keyboardController?.hide() //
                            // Automatically drops focus, which minimizes the keyboard
                            // Optionally, you can also trigger onSendOtpClick() right here if you want!
                            // onSendOtpClick()
                        }
                    ),

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

                                color = Color(0xFF101828)
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
                                        Color(0xFFD0D5DD)
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

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.Center,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Don't have an account?",

                        style =
                            MaterialTheme.typography
                                .bodyMedium,

                        color =
                            MaterialTheme.colorScheme
                                .textSecondary
                    )

                    Spacer(
                        modifier =
                            Modifier.width(6.dp)
                    )

                    Text(
                        text = "Create account",

                        style =
                            MaterialTheme.typography
                                .labelMedium,

                        color =
                            MaterialTheme.colorScheme
                                .secondary,

                        modifier = Modifier.clickable {

                            onNavigateToRegister()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

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

            iconRes =
                R.drawable.ic_login
        )
    }
}