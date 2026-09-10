package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.AuthHeaderPattern
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanUiState
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.datePicker.AppDatePickerDialog
import com.policyboss.customer.ui.components.datePicker.DateConstraint
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.textPrimary


@Composable
fun VerifyPanScreen(
    uiState: VerifyPanUiState,
    onAction: (VerifyPanAction) -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    if (uiState.showDatePicker) {
        AppDatePickerDialog(
            onDateSelected = { onAction(VerifyPanAction.OnDobSelected(it)) },
            onDismiss = { onAction(VerifyPanAction.OnDismissDatePicker) },
            dateConstraint = DateConstraint.PastOnly
        )
    }

    // ==========================================
    // 1. ROOT SCREEN BOX
    // ==========================================
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary) // Matches PrivilegeEmailPanContent
    ) {
        // Background waves pattern
        AuthHeaderPattern()

        Column(modifier = Modifier.fillMaxSize()) {

            // Top App Bar
            AppTopBar(
                title = "",
                onBackClick = onBackClick,
                backIconTint = AppColors.White,
                trailingIcon = painterResource(id = R.drawable.ic_close),
                onTrailingClick = onCloseClick,
                trailingIconTint = AppColors.White
            )

            // Header Texts
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Verify PAN details",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Confirm below details and continue",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.White.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // 2. OVERLAPPING CONTENT AREA
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                // We do NOT apply the background here directly, otherwise it blocks the cards.
            ) {

                // BACKGROUND CURVE (Starts lower down to sit behind Card 1)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 56.dp) // Pushes background down so Card 1 overlaps it
                        .background(
                            color = Color(0xFFF8FAFC),
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                )

                // FOREGROUND SCROLLABLE CONTENT (Scrolls over the background)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .imePadding()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(scrollState)
                            .padding(horizontal = 24.dp)
                    ) {

                        // Small spacer so Card 1 overlaps exactly where the background starts
                        Spacer(modifier = Modifier.height(16.dp))

                        // ------------------------------------------
                        // CARD 1: PAN Verify
                        // ------------------------------------------
                        Card(
                            colors = CardDefaults.cardColors(containerColor = AppColors.White),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Permanent account number card",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.textPrimary
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(0xFFF1F5F9), // Subtle grey inner background
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .padding(horizontal = 16.dp, vertical = 14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = uiState.panNumber,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.textPrimary
                                    )
                                    if (uiState.isPanValid) {
                                        Text(
                                            text = "Valid",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFF10B981), // Green
                                            modifier = Modifier
                                                .background(
                                                    color = Color(0xFFD1FAE5),
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // ------------------------------------------
                        // CARD 2: Personal Detail
                        // ------------------------------------------
                        Card(
                            colors = CardDefaults.cardColors(containerColor = AppColors.White),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {

                                // Full Name
                                Text(
                                    text = "Full Name",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.textPrimary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                AppOutlinedTextField(
                                    value = uiState.fullName,
                                    onValueChange = { onAction(VerifyPanAction.OnFullNameChanged(it)) },
                                    placeholder = "Enter full name",
                                    isError = uiState.isFullNameError,
                                    errorMessage = uiState.fullNameErrorMessage,
                                    keyboardOptions = KeyboardOptions(
                                        capitalization = KeyboardCapitalization.Words,
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // Date of Birth
                                Text(
                                    text = "Date of birth",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.textPrimary
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            focusManager.clearFocus()
                                            onAction(VerifyPanAction.OnDobClick)
                                        }
                                ) {
                                    AppOutlinedTextField(
                                        value = uiState.dob,
                                        onValueChange = {},
                                        placeholder = "DD/MM/YYYY",
                                        isError = uiState.isDobError,
                                        errorMessage = uiState.dobErrorMessage,
                                        enabled = false,
                                        modifier = Modifier.fillMaxWidth(),
                                       // trailingIcon = painterResource(id = R.drawable.ic_chevron_right)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))
                    }

                    // ------------------------------------------
                    // STICKY BOTTOM BUTTON
                    // ------------------------------------------
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF8FAFC)) // Match the background curve
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        PrimaryCTAButton(
                            text = "Confirm and Continue",
                            onClick = {
                                focusManager.clearFocus()
                                onAction(VerifyPanAction.OnConfirmClick)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun VerifyPanContentPreview() {
    PolicyBossCustomerTheme {
        VerifyPanScreen(
            uiState = VerifyPanUiState(
                fullName = "Dhruvi Desai",
                dob = "29/08/2001"
            ),
            onAction = {},
            onBackClick = {},
            onCloseClick = {}
        )
    }
}