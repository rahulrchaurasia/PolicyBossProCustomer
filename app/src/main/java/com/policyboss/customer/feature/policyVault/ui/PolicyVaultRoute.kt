package com.policyboss.customer.feature.policyVault.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.customer.feature.claimSupport.ui.ClaimSupportScreen
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiEvent
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiState
import com.policyboss.customer.feature.privilege.viewmodel.PrivilegeViewModel
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme



@Composable
fun PolicyVaultRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from MainScreen Scaffold
    viewModel: PrivilegeViewModel = hiltViewModel(),
    onNavigateToQuiz: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val context = LocalContext.current

    // Listen for one-time events
    LaunchedEffect(viewModel.uiEvent, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is PrivilegeUiEvent.NavigateToQuiz -> onNavigateToQuiz()
//                    is PrivilegeUiEvent.OpenDialer -> {
//                        val intent = Intent(
//                            Intent.ACTION_DIAL,
//                            "tel:${event.phoneNumber}".toUri()
//                        )
//                        context.startActivity(intent)
//                    }
                    is PrivilegeUiEvent.ShowSnackbar -> { /* Handle Snackbar */ }
                }
            }
        }
    }

    // Render the stateless screen
    ClaimSupportScreen(
        modifier = modifier,
        contentPadding = contentPadding, // Pass the padding down!
        uiState = uiState,
        onAction = { action ->
            when (action) {
                // If it's pure navigation that doesn't need VM logic, you can intercept here.
                // Otherwise, pass it to the ViewModel:
                else -> viewModel.onAction(action)
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
            uiState = PrivilegeUiState(),
            onAction = {}
        )
    }
}