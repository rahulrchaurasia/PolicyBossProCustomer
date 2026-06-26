package com.policyboss.customer.feature.privilege.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeAction
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiState

@Composable
fun PrivilegeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: PrivilegeUiState, // Your data class from ViewModel
    onAction: (PrivilegeAction) -> Unit
) {
    // We use Box or Column at the root, applying the Scaffold padding from MainScreen
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            // Add extra bottom padding if you want spacing above the nav bar
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {

//            // 1. Hero Section (Yellow Banner)
//            item {
//                PrivilegeHeroSection(
//                    onSetupClick = { onAction(PrivilegeAction.OnSetupAccountClick) }
//                )
//            }
//
//            // 2. Setup Steps Tracker
//            item {
//                PrivilegeStepsSection(
//                    // Pass state here, e.g., currentStep = uiState.currentSetupStep
//                )
//            }
//
//            // 3. Benefits Section
//            item {
//                PrivilegeBenefitsSection()
//            }
//
//            // 4. Pricing / Final CTA
//            item {
//                PrivilegePricingSection(
//                    onJoinClick = { onAction(PrivilegeAction.OnJoinPrivilegeClick) }
//                )
//            }
//
//            // 5. Support / Assistance
//            item {
//                PrivilegeSupportSection(
//                    onAssistanceClick = { onAction(PrivilegeAction.OnGetAssistanceClick) }
//                )
//            }
//
//            // 6. Trust Badges
//            item {
//                PrivilegeTrustBadgesSection()
//            }
        }
    }
}