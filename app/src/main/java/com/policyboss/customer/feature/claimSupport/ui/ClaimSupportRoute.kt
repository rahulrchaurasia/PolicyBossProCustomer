package com.policyboss.customer.feature.claimSupport.ui

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
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportAction
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportUiEvent
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportUiState
import com.policyboss.customer.feature.claimSupport.viewmodel.ClaimSupportViewModel
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun ClaimSupportRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from MainScreen Scaffold
    viewModel: ClaimSupportViewModel = hiltViewModel(),
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
                    is ClaimSupportUiEvent.ShowSnackbar -> {

                    }
                    is ClaimSupportUiEvent.event1 -> {

                    }
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
                ClaimSupportAction.firstClick -> {}
                ClaimSupportAction.secondClick -> {}
            }
        }
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
            uiState = ClaimSupportUiState(),
            onAction = {}
        )
    }
}