package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.viewmodel.ClaimGuideViewModel
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

@Composable
fun ClaimGuideRoute(
    productType: AddPolicyType, // E.g., "Motor", "Health", "Travel"// // Passed in from your NavGraph (FileClaim -> ClaimGuide)
    viewModel: ClaimGuideViewModel, // Personal ViewModel for this screen
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // You can collect state from the viewModel here if fetching dynamic blog content
    // val uiState by viewModel.uiState.collectAsState()

    ClaimGuideScreen(
        productType =  productType,
        onBackClick = onBackClick,
        modifier = modifier
    )
}