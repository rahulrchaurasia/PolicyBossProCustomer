package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.LookupType

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.datePicker.AppDatePickerDialog
import com.policyboss.customer.ui.components.datePicker.AppTimePickerDialog
import com.policyboss.customer.ui.components.datePicker.DateConstraint
import com.policyboss.customer.ui.components.progreebar.AppStepProgressBar
import com.policyboss.customer.ui.components.text.FormLabel
import com.policyboss.customer.ui.components.textfield.AppClickableTextField
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

// ==============================================================================
// ⭐ Recomposition : observing the state via collectAsStateWithLifecycle().
// ==============================================================================
//Step 3: The Reaction (Recomposition): Your AccidentDetailsScreen is continuously
// observing the state via collectAsStateWithLifecycle(). The absolute microsecond
// the ViewModel updates that state, Compose notices the change and automatically redraws (recomposes) the AppOutlinedTextField with the new address string.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccidentDetailsScreen(
    uiState: AccidentDetailsUiState,
    onAction: (AccidentDetailsAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Dialog visibility states
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    // ==========================================
    // ⭐ KEYBOARD & FOCUS MANAGEMENT SETUP
    // ==========================================
    val focusManager = LocalFocusManager.current

    // Create FocusRequesters only for fields that accept typing
    val (locationFocusRequester, descriptionFocusRequester) = remember { FocusRequester.createRefs() }

    // Dynamically change keyboard capitalization based on toggle
    val lookupCapitalization = if (uiState.selectedLookupType == LookupType.VEHICLE_NUMBER) {
        androidx.compose.ui.text.input.KeyboardCapitalization.Characters
    } else {
        androidx.compose.ui.text.input.KeyboardCapitalization.None
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)

    )
    {
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
        )
        {
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

            // 1. Dynamic Capitalization (Only needed for text/vehicle plates)
            val lookupCapitalization = if (uiState.selectedLookupType == LookupType.VEHICLE_NUMBER) {
                KeyboardCapitalization.Characters
            } else {
                KeyboardCapitalization.None // Policy numbers don't need caps if they are digits
            }

            // 🚀 2. Dynamic Keyboard Type (Number pad for Policy, QWERTY for Vehicle)
            val keyboardType = if (uiState.selectedLookupType == LookupType.VEHICLE_NUMBER) {
                KeyboardType.Text // Standard QWERTY keyboard with letters/numbers
            } else {
                KeyboardType.Number // 🔢 Opens the pure number pad!
            }
            // ==========================================
            // ⭐  Lookup Value Field
            // ==========================================
            AppOutlinedTextField(
                value = uiState.lookupValue,
                onValueChange = { onAction(AccidentDetailsAction.OnLookupValueChanged(it)) },
                placeholder = if (uiState.selectedLookupType == LookupType.VEHICLE_NUMBER) "MH-123456" else "Policy Number",
                isError = uiState.lookupError != null,
                errorMessage = uiState.lookupError,
                // ⭐ Apply dynamic capitalization and set ImeAction to Next
                keyboardOptions = KeyboardOptions(
                    capitalization = lookupCapitalization,
                    keyboardType = keyboardType, // Changes based on the tab!
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // ⭐ Jump straight to Location, skipping Date and Time!
                        locationFocusRequester.requestFocus()
                    }
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Date of Incident
            // ==========================================
            FormLabel("Date of incident")
            AppClickableTextField(
                value = uiState.incidentDate,
                placeholder = "DD/MM/YYYY",
                onClick = {
                    focusManager.clearFocus()
                    showDatePicker = true },
                isError = uiState.dateError != null,
                errorMessage = uiState.dateError,
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_calendar),  tint = AppColors.TextSecondary, contentDescription = "Select Date") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // ⭐ REFACTORED: Time of Incident
            // ==========================================
            FormLabel("Time of incident")
            AppClickableTextField(
                value = uiState.incidentTime,
                placeholder = "HH:MM",
                onClick = {
                    focusManager.clearFocus() // ⭐ Drop keyboard if open when dialog is clicked
                    showTimePicker = true
                },
                isError = uiState.timeError != null,
                errorMessage = uiState.timeError,
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_calendar), tint = AppColors.TextSecondary, contentDescription = "Select Time") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // ==========================================
            // Location (To be handled properly later)
            // ==========================================
            FormLabel("Location")
            AppOutlinedTextField(
                value = uiState.location,
                onValueChange = { onAction(AccidentDetailsAction.OnLocationChanged(it)) },
                placeholder = "Enter accident location",
                readOnly = uiState.isAutoLocation, // Locked when auto-fetched
                isError = uiState.locationError != null,
                errorMessage = uiState.locationError,
                modifier = Modifier.focusRequester(locationFocusRequester),

                // 🚀 UI FIX: Enable multi-line for long detailed addresses
                singleLine = false,
                minLines = 2, // Always show at least 2 lines of height so it looks like a text area
                maxLines = 4, // Cap it at 4 lines so it doesn't break the layout


                // 🚀 IT GOES RIGHT HERE!
                trailingContent = if (uiState.location.isNotBlank()) {
                    {
                        // 🚀 UI FIX: Wrap in a Column to force top-alignment in multi-line fields
                        Column(
                            modifier = Modifier.height(72.dp), // Approximate height of 2-3 lines
                            verticalArrangement = Arrangement.Top
                        ) {
                            androidx.compose.material3.IconButton(
                                onClick = { onAction(AccidentDetailsAction.OnClearLocation) },
                                modifier = Modifier.padding(top = 4.dp) // Tweaks alignment to match text
                            ) {
                                Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.Close,
                                    contentDescription = "Clear Location",
                                    tint = AppColors.TextSecondary
                                )
                            }
                        }
                    }
                } else null,
                // 🚀 END OF TRAILING CONTENT

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),

                keyboardActions = KeyboardActions(
                    onNext = { descriptionFocusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Use Current Location Button
            OutlinedButton(
                // 🚀 FIX: Updated to OnUseCurrentLocationClick so the Route catches it!
                onClick = { onAction(AccidentDetailsAction.OnUseCurrentLocationClick) },
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done // ⭐ Final field
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus() // ⭐ Drop the keyboard
                    }
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        // 4. Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.ClaimLightBg)
                .navigationBarsPadding()            // 2. Add Nav Bar space
                .imePadding()                       // 3. Add Keyboard space
                .padding(horizontal = 20.dp, vertical = 16.dp),
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

    // ==========================================
    // Dialogs
    // ==========================================
    if (showDatePicker) {
        AppDatePickerDialog(
            dateConstraint = DateConstraint.PastMonths(1), // 👈 Exactly 1 month ago until today

            onDateSelected = { formattedDate ->
                onAction(AccidentDetailsAction.OnDateChanged(formattedDate))
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        AppTimePickerDialog(
            onTimeSelected = { formattedTime ->
                onAction(AccidentDetailsAction.OnTimeChanged(formattedTime))
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
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