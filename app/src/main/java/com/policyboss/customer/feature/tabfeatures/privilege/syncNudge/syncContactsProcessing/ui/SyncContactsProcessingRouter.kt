package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.ui

// NOTE: Ensure these match your actual project package structure




import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.viemodel.SyncContactsProcessingViewModel


@Composable
fun SyncContactsProcessingRouter(
    viewModel: SyncContactsProcessingViewModel,
    onNavigateToHome: () -> Unit,
    modifier : Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SyncContactsProcessingScreen(
        state = uiState,
        onCompleteProfileClicked = onNavigateToHome
    )
}

