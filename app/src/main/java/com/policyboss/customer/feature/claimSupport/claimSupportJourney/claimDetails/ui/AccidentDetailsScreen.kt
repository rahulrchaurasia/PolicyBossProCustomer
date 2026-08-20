package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.progreebar.AppStepProgressBar
import com.policyboss.customer.ui.components.text.FormLabel
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccidentDetailsScreen(
    uiState: AccidentDetailsUiState,
    onAction: (AccidentDetailsAction) -> Unit,
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

        // 2. Progress Bar
//        LinearProgressIndicator(
//            progress = { 0.2f }, // 1/5
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp)
//                .height(4.dp)
//                .clip(RoundedCornerShape(50)), // ⭐ 1. Add this to round the edges manually
//            color = AppColors.PrimaryBlue,
//            trackColor = AppColors.BorderSecondary,
//            strokeCap = StrokeCap.Butt // ⭐ 2. Add this to disable the buggy native rounding
//        )

        AppStepProgressBar(
            currentStep = 1,
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

            Text(text = "1/5", fontSize = 14.sp, color = AppColors.TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Accident details",
                style = MaterialTheme.typography.headlineSmall,
                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Segmented Control
            SegmentedLookupControl(
                selectedType = uiState.selectedLookupType,
                onTypeSelected = { onAction(AccidentDetailsAction.OnLookupTypeChanged(it)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // ==========================================
            // ⭐ REFACTORED: Lookup Value Field
            // ==========================================
            AppOutlinedTextField(
                value = uiState.lookupValue,
                onValueChange = { onAction(AccidentDetailsAction.OnLookupValueChanged(it)) },
                placeholder = if (uiState.selectedLookupType == LookupType.VEHICLE_NUMBER) "MH-123456" else "Policy Number"
            )
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Date of Incident
            // ==========================================
            FormLabel("Date of incident")
            AppOutlinedTextField(
                value = uiState.incidentDate,
                onValueChange = { onAction(AccidentDetailsAction.OnDateChanged(it)) },
                placeholder = "DD/MM/YYYY",
                trailingContent = { Icon(painterResource(id = R.drawable.ic_calendar), contentDescription = null) }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Time of Incident
            // ==========================================
            FormLabel("Time of incident")
            AppOutlinedTextField(
                value = uiState.incidentTime,
                onValueChange = { onAction(AccidentDetailsAction.OnTimeChanged(it)) },
                placeholder = "HH:MM",
                trailingContent = { Icon(painterResource(id = R.drawable.ic_calendar), contentDescription = null) }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Location
            // ==========================================
            FormLabel("Location")
            AppOutlinedTextField(
                value = uiState.location,
                onValueChange = { onAction(AccidentDetailsAction.OnLocationChanged(it)) },
                trailingContent = { Icon(painterResource(id = R.drawable.ic_chevron_left), contentDescription = null) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Use Current Location Button
            OutlinedButton(
                onClick = { onAction(AccidentDetailsAction.OnUseCurrentLocation) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                border = borderStroke(1.dp, AppColors.PrimaryBlue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColors.PrimaryBlue)
            ) {
                Icon(painterResource(id = R.drawable.ic_location), contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Use Current Location", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Description
            // ==========================================
            FormLabel("Description of Accident (opt.)")
            AppOutlinedTextField(
                value = uiState.description,
                onValueChange = { onAction(AccidentDetailsAction.OnDescriptionChanged(it)) },
                modifier = Modifier.height(100.dp), // Height applied here works perfectly now
                singleLine = false, // Allow multi-line for description
                minLines = 3,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done) // Shows "Done" to dismiss keyboard
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        // 4. Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.ClaimLightBg)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryCTAButton(
                text = "Continue",
                onClick = { onAction(AccidentDetailsAction.OnContinueClick) },
                contentColor = AppColors.White,
                arrowBackgroundColor = AppColors.White,
                arrowTint = AppColors.ClaimDarkBg
            )
        }
    }
}

// ... Segmented Controls & Previews remain the same ...


@Composable
fun SegmentedLookupControl(
    selectedType: LookupType,
    onTypeSelected: (LookupType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, AppColors.BorderSecondary, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        SegmentedButton(
            text = "Policy number",
            isSelected = selectedType == LookupType.POLICY_NUMBER,
            onClick = { onTypeSelected(LookupType.POLICY_NUMBER) },
            modifier = Modifier.weight(1f)
        )
        SegmentedButton(
            text = "Vehicle number",
            isSelected = selectedType == LookupType.VEHICLE_NUMBER,
            onClick = { onTypeSelected(LookupType.VEHICLE_NUMBER) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SegmentedButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) AppColors.SupportCardBackground else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) AppColors.PrimaryBlue else AppColors.TextSecondary,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

// Add borderStroke helper if not already in your project
fun borderStroke(width: androidx.compose.ui.unit.Dp, color: Color) = androidx.compose.foundation.BorderStroke(width, color)



// ─────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true, name = "Accident Details Preview")
@Composable
fun AccidentDetailsScreenPreview() {
    MaterialTheme {
        AccidentDetailsScreen(
            uiState = AccidentDetailsUiState(
                selectedLookupType = LookupType.VEHICLE_NUMBER,
                lookupValue = "MH-123456",
                incidentDate = "29/02/2026",
                incidentTime = "29/02/2026", // Matching the screenshot exactly
                location = "Mumbai, Maharashtra",
                description = ""
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Accident Details Empty Preview")
@Composable
fun AccidentDetailsScreenEmptyPreview() {
    MaterialTheme {
        AccidentDetailsScreen(
            uiState = AccidentDetailsUiState(
                selectedLookupType = LookupType.POLICY_NUMBER, // Testing the other toggle state
                lookupValue = "",
                incidentDate = "",
                incidentTime = "",
                location = "",
                description = ""
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ }
        )
    }
}