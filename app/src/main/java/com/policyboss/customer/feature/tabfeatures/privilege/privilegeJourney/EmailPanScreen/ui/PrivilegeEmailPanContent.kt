package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.policyboss.customer.feature.login.component.AuthHeaderPattern
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanUiState
import com.policyboss.customer.ui.components.bullet.AppInfoBox
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.textfield.PanTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.components.toolbarHeader.AuthCompositeHeaderIcon
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary

@Composable
fun PrivilegeEmailPanContent(
    uiState: EmailPanUiState,
    onEmailChange: (String) -> Unit,
    onPanChange: (String) -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val panFocusRequester = remember { FocusRequester() }

    // ==========================================
    // 1. ROOT SCREEN BOX (Dark Background)
    // ==========================================
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        // Background waves pattern
        AuthHeaderPattern()

        // Top App Bar
        AppTopBar(
            title = "",
            onBackClick = onBackClick,
            backIconTint = AppColors.White,
            modifier = Modifier.zIndex(1f)
        )

        // ==========================================
        // 2. WHITE BOTTOM SHEET BOX
        // ==========================================
        // This box dynamically takes up 82% of whatever screen it is running on.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.82f)
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
        ) {

            // Layout for scrollable content and sticky button
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .imePadding() // Ensures button slides up when keyboard opens
            ) {
                // Scrollable Form Area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                        .padding(horizontal = 24.dp)
                        .padding(top = 64.dp) // Space for the floating icon
                ) {
                    Text(
                        text = "Enter your email & PAN",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.textPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Verify yourself to open an account",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.textSecondary
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(text = "Email",
                        style = MaterialTheme.typography.headlineSmall,
                        color = AppColors.TextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AppOutlinedTextField(
                        value = uiState.email,
                        onValueChange = onEmailChange,
                        placeholder = "Enter your existing email",
                        isError = uiState.isEmailError,
                        errorMessage = uiState.emailErrorMessage,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { panFocusRequester.requestFocus() }),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Permanent account number",
                     style = MaterialTheme.typography.headlineSmall,
                        color = AppColors.TextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
//                    AppOutlinedTextField(
//                        modifier = Modifier.fillMaxWidth().focusRequester(panFocusRequester),
//                        value = uiState.panNumber,
//                        onValueChange = onPanChange,
//                        placeholder = "Enter your PAN",
//                        isError = uiState.isPanError,
//                        errorMessage = uiState.panErrorMessage,
//                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
//                        keyboardActions = KeyboardActions(
//                            onDone = {
//                                focusManager.clearFocus()
//                                onContinueClick()
//                            }
//                        )
//                    )

                    PanTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(panFocusRequester),

                        value = uiState.panNumber,

                        onValueChange = onPanChange,

                        placeholder = "Enter your PAN",

                        isError = uiState.isPanError,

                        errorMessage = uiState.panErrorMessage,

                        imeAction = ImeAction.Done,

                        onDone = {
                            focusManager.clearFocus()
                            onContinueClick()
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AppInfoBox(
                        title = "Required as per IRDA guidelines",
                        descriptionItems = listOf(
                            "To confirm legal identity",
                            "To avoid impersonation",
                            "To match with government databases"
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Sticky CTA Button
                PrimaryCTAButton(
                    text = "Continue",
                    onClick = {
                        focusManager.clearFocus()
                        onContinueClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }

            // ==========================================
            // 3. FLOATING ICON (INSIDE THE WHITE BOX)
            // ==========================================
            val iconSize = 96.dp

            AuthCompositeHeaderIcon(
                size = iconSize,
                modifier = Modifier
                    .align(Alignment.TopCenter) // Anchors to the top curve of the WHITE box
                    .offset(y = -(iconSize / 2)) // Pushes it exactly 50% upwards (48.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PrivilegeEmailPanContentPreview() {
    PolicyBossCustomerTheme {
        PrivilegeEmailPanContent(
            uiState = EmailPanUiState(
                email = "rahul@policyboss.com",
                panNumber = "ABCDE1234F"
            ),
            onEmailChange = {},
            onPanChange = {},
            onContinueClick = {},
            onBackClick = {}
        )
    }
}