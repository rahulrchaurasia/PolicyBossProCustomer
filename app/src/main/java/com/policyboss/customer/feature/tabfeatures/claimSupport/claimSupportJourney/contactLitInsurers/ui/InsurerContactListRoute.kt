package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.viewmodel.InsurerContactListViewModel


@Composable
fun InsurerContactListRoute(
    productName: String,
    viewModel: InsurerContactListViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Handle One-Time Events (like toasts for clicks)
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is InsurerContactListEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    InsurerContactListScreen(
        productName = productName, // E.g., passed down from args.productType.name
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onNavigateBack
    )
}