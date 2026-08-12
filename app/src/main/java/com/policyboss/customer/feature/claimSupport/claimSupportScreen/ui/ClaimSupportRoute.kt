package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ClaimSupportUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ClaimUiState
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimViewModel
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun ClaimSupportRoute(
    viewModel: ClaimViewModel,
    contentPadding: PaddingValues,
    // Define explicit navigation callbacks for clarity
    onNavigateToFileClaim: (AddPolicyType) -> Unit,
    onNavigateToClaimGuide: (AddPolicyType) -> Unit,
    onNavigateToCashlessGarage: () -> Unit,
    onNavigateToInsurerContacts: () -> Unit,
    onNavigateToFaqs: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    // Listen for one-time events
    LaunchedEffect(viewModel.uiEvent, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is ClaimSupportUiEvent.NavigateToFileClaimFlow -> {
                        onNavigateToFileClaim(event.product)
                    }
                    is ClaimSupportUiEvent.NavigateToClaimGuide -> {
                        onNavigateToClaimGuide(event.product)
                    }
                    ClaimSupportUiEvent.OpenCashlessGarage -> {
                        onNavigateToCashlessGarage()
                    }
                    ClaimSupportUiEvent.OpenInsurerContacts -> {
                        onNavigateToInsurerContacts()
                    }
                    ClaimSupportUiEvent.OpenFaq -> {
                        onNavigateToFaqs()
                    }
                    ClaimSupportUiEvent.OpenSupportDialer -> {
                        // Handle dialer logic (e.g., using Intent(Intent.ACTION_DIAL))
                    }
                    is ClaimSupportUiEvent.ShowSnackbar -> {
                        // Handle Snackbar
                    }


                    else -> {}
                }
            }
        }
    }

    // Render the stateless screen
    ClaimSupportScreen(
        uiState = uiState,
        contentPadding = contentPadding,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Preview(
    name = "Claim Support Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ClaimSupportScreenPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportScreen(
            modifier = Modifier,
            contentPadding = PaddingValues(),
            uiState = ClaimUiState(),
            onAction = {}
        )
    }
}