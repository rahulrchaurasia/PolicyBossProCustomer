package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.policyboss.customer.R
import com.policyboss.customer.feature.login.component.AuthHeaderPattern
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.VehicleType
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui.component.InsurerPickerBottomSheet
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui.component.VehicleSelectionRow
import com.policyboss.customer.ui.components.button.AppUseCurrentLocationButton
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.text.FormLabel
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors



@Composable
fun CashlessGarageScreen(
    uiState: CashlessGarageUiState,
    onAction: (CashlessGarageAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var showInsurerSheet by remember { mutableStateOf(false) }

    // ==========================================
    // 1. ROOT BOX
    // ==========================================
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.ClaimDarkBg) // Use your dark blue color here
    ) {

        // 🚀 FIX: AuthHeaderPattern MUST be here in the Box, outside the Column!
        // This makes it draw as a background without pushing other elements down.
        AuthHeaderPattern()

        // ==========================================
        // 2. FOREGROUND COLUMN
        // ==========================================
        Column(modifier = Modifier.fillMaxSize()) {

            // 🚀 FIX: Empty title string to match the screenshot design
            AppTopBar(
                title = "",
                onBackClick = onBackClick,
                backIconTint = AppColors.White,
                modifier = Modifier.zIndex(1f)
            )

            // 🚀 FIX: Header Text placed directly below the TopBar
            Text(
                text = "Cashless Garages",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.White,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ==========================================
            // 3. WHITE BOTTOM SHEET AREA
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // 🚀 FIX: Takes up 100% of the remaining space seamlessly
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .imePadding()
                ) {
                    // Scrollable Content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(scrollState)
                            .padding(horizontal = 24.dp, vertical = 24.dp)
                    ) {
                        // --- Vehicle Type Selection ---
                        FormLabel("Vehicle type")
                        Spacer(modifier = Modifier.height(8.dp))

                        VehicleType.entries.forEach { type ->
                            VehicleSelectionRow(
                                type = type,
                                isSelected = uiState.selectedVehicle == type,
                                onClick = { onAction(CashlessGarageAction.OnVehicleTypeSelected(type)) }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- Location Input ---
                        FormLabel("Location")
                        Spacer(modifier = Modifier.height(8.dp))
                        AppOutlinedTextField(
                            value = uiState.location,
                            onValueChange = { onAction(CashlessGarageAction.OnLocationChanged(it)) },
                            placeholder = "Enter city, pincode, or full address",
                            readOnly = uiState.isAutoLocation,
                            isError = uiState.locationError != null,
                            errorMessage = uiState.locationError,
                            singleLine = false,
                            minLines = 2,
                            maxLines = 4,
                            trailingContent = if (uiState.location.isNotBlank()) {
                                {
                                    Column(
                                        modifier = Modifier.height(72.dp),
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        androidx.compose.material3.IconButton(
                                            onClick = { onAction(CashlessGarageAction.OnClearLocation) },
                                            modifier = Modifier.padding(top = 4.dp)
                                        ) {
                                            Icon(
                                                imageVector = androidx.compose.material.icons.Icons.Default.Close,
                                                contentDescription = "Clear Location",
                                                tint = AppColors.TextSecondary
                                            )
                                        }
                                    }
                                }
                            } else {
                                // 🚀 Added standard dropdown chevron if empty (matches screenshot)
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_chevron_down),
                                        contentDescription = null,
                                        tint = AppColors.TextPrimary
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        AppUseCurrentLocationButton(
                            onClick = { onAction(CashlessGarageAction.OnUseCurrentLocationClick) },
                            modifier = modifier
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // --- Insurer Input ---
                        FormLabel("Insurer")
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = uiState.selectedInsurer?.name ?: "Select Insurer",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth(),
                                // 🚀 Added dropdown chevron for Insurer (matches screenshot)
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_chevron_down),
                                        contentDescription = null,
                                        tint = AppColors.TextPrimary
                                    )
                                }
                            )

                            // Invisible overlay that intercepts the click
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable { showInsurerSheet = true }
                            )
                        }
                    }

                    // Sticky Submit Button
                    PrimaryCTAButton(
                        text = "Find Garages",
                        onClick = { onAction(CashlessGarageAction.OnFindGaragesClick) },
                        contentColor = AppColors.White,
                        arrowBackgroundColor = AppColors.White,
                        arrowTint = AppColors.ClaimDarkBg,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }

    if (showInsurerSheet) {
        InsurerPickerBottomSheet(
            insurers = uiState.insurers,
            selectedInsurer = uiState.selectedInsurer,
            onInsurerSelected = {
                onAction(CashlessGarageAction.OnInsurerSelected(it))
                showInsurerSheet = false
            },
            onDismiss = { showInsurerSheet = false }
        )
    }
}