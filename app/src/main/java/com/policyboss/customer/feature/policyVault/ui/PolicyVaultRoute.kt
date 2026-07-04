package com.policyboss.customer.feature.policyVault.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiState
import com.policyboss.customer.feature.policyVault.viewmodel.PolicyVaultViewModel
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun PolicyVaultRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from MainScreen Scaffold
    viewModel: PolicyVaultViewModel = hiltViewModel(),
    onNavigateToQuiz: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Render the stateless screen
    PolicyVaultScreen(
        modifier = modifier,
        contentPadding = contentPadding, // Pass the padding down!
        uiState = uiState,
        onAction = { action ->
            when (action) {
                PolicyVaultAction.firstClick -> {}
                PolicyVaultAction.secondClick -> {}
            }
        }
    )
}

@Preview(
    name = "Policy Vault Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PolicyVaultScreenPreview() {

    PolicyBossCustomerTheme {

        PolicyVaultScreen(
            modifier = Modifier,
            contentPadding = PaddingValues(),
            uiState = PolicyVaultUiState(),
            onAction = {}
        )
    }
}