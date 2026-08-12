package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.viewmodel.ClaimGuideViewModel
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

@Composable
fun FileClaimRoute(

    productType: AddPolicyType,
    viewModel: ClaimGuideViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Pass the shared data to the child ViewModel safely
    // The key ensures it only re-fetches if the product actually changes
    LaunchedEffect(productType) {
        viewModel.fetchGuideForProduct(productType)
    }



    FileClaimScreen(
        productType = productType,
        onBackClick = onBackClick,
        onCloseClick = onBackClick,
        onContinueClick = {
            // e.g., viewModel.onAction(FileClaimAction.Submit)
            // or navigate to the next screen
        }
    )
}