package com.policyboss.customer.feature.policyVault.ui.addManualPolicy

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.customer.feature.policyVault.model.AddPolicyState.AddPolicyEvent
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.feature.policyVault.viewmodel.AddManualPolicyViewModel

import com.policyboss.customer.feature.policyVault.model.addPolicyState.AddPolicyUiState

// Also ensure you have the delegate import for 'by'
import androidx.compose.runtime.getValue
@Composable
fun AddManualPolicyRoute(
    viewModel: AddManualPolicyViewModel, // Passed in via DI in your NavGraph
    contentPadding: PaddingValues,
    policyType: AddPolicyType,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onNavigateBack: () -> Unit

) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    // 1. Get the Compose URI Handler here
    val uriHandler = LocalUriHandler.current


    // Listen for one-time events (like navigation)
    LaunchedEffect(viewModel.uiEvent, lifecycleOwner) {
        // This ensures collection PAUSES when the app goes to the background
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            // Use collect instead of collectLatest!
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is AddPolicyEvent.ShowToast -> {
                        // Fire the standard Android Toast
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is AddPolicyEvent.NavigateBack -> {
                        onNavigateBack()
                    }
                }
            }

        }
    }

    // Pass the state and action dispatcher to the stateless UI
    AddManualPolicyScreen(

        uiState = uiState,
        contentPadding = contentPadding,
        policyType = policyType,
        onAction = viewModel::onAction,
        onBackClick = onBackClick,
        onCloseClick = onCloseClick
    )
}