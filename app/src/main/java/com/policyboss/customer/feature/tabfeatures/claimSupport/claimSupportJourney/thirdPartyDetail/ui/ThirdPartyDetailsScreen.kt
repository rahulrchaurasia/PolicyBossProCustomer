package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.progreebar.AppStepProgressBar
import com.policyboss.customer.ui.components.text.FormLabel
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ThirdPartyDetailsScreen(
    uiState: ThirdPartyDetailsUiState,
    onAction: (ThirdPartyDetailsAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. Top Bar
        AppTopBar(
            title = "Raise a claim",
            onBackClick = onBackClick,
            trailingIcon = painterResource(id = R.drawable.ic_settings),
            onTrailingClick = { /* Actions */ },
            titleColor = AppColors.TextPrimary,
            backIconTint = AppColors.TextPrimary,
            trailingIconTint = AppColors.TextPrimary
        )



        AppStepProgressBar(
            currentStep = 2,
            totalSteps = 5,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        // 3. Scrollable Form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "2/5",
                fontSize = 14.sp,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Other Parties (If applicable)",
                style = MaterialTheme.typography.headlineMedium,
                color = AppColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Other Driver's Name
            FormLabel("Other driver’s name")
            AppOutlinedTextField(
                value = uiState.driverName,
                onValueChange = { onAction(ThirdPartyDetailsAction.OnDriverNameChanged(it)) },
                placeholder = "Enter driver's name",
                isError = uiState.nameError != null,       // Added
                errorMessage = uiState.nameError,          // Added
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Phone Number Field with +91 Prefix & Divider
            FormLabel("Phone number")
            AppOutlinedTextField(
                value = uiState.phoneNumber,
                onValueChange = { onAction(ThirdPartyDetailsAction.OnPhoneNumberChanged(it)) },
                placeholder = "Enter phone number",
                isError = uiState.phoneError != null,      // Added
                errorMessage = uiState.phoneError,         // Added
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                leadingContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "+91",
                            color = AppColors.TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        VerticalDivider(
                            modifier = Modifier.height(24.dp),
                            thickness = 1.dp,
                            color = AppColors.BorderPrimary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        // 4. Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // PRO UX TIP: Change button text dynamically!
            val buttonText = if (uiState.driverName.isBlank() && uiState.phoneNumber.isBlank()) {
                "Skip & Continue"
            } else {
                "Confirm & Continue"
            }

            PrimaryCTAButton(
                text = buttonText,
                onClick = { onAction(ThirdPartyDetailsAction.OnContinueClick) },
                contentColor = AppColors.White,
                arrowBackgroundColor = AppColors.White,
                arrowTint = AppColors.ClaimDarkBg
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true, name = "Third Party Details Preview")
@Composable
fun ThirdPartyDetailsScreenPreview() {
    MaterialTheme {
        ThirdPartyDetailsScreen(
            uiState = ThirdPartyDetailsUiState(
                driverName = "Rishi Maheswari",
                phoneNumber = "7045020378"
            ),
            onAction = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Third Party Details Empty")
@Composable
fun ThirdPartyDetailsScreenEmptyPreview() {
    MaterialTheme {
        ThirdPartyDetailsScreen(
            uiState = ThirdPartyDetailsUiState(
                driverName = "",
                phoneNumber = ""
            ),
            onAction = {},
            onBackClick = {}
        )
    }
}