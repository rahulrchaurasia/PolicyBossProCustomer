package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.ui


import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state.SyncContactsAction
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state.SyncContactsEvent
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.viemodel.SyncContactsViewModel
import com.policyboss.customer.utils.permission.PermissionRationaleDialog

@Composable
fun SyncContactsRoute(
    viewModel: SyncContactsViewModel,
    onSyncContactsSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // State to control your custom Rationale Dialog
    var showPermissionDialog by remember { mutableStateOf(false) }

    // 1. Setup the Permission Launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission approved! Tell ViewModel to navigate.
            viewModel.onPermissionGranted()
        } else {
            // Permission denied! Show the rationale dialog.
            showPermissionDialog = true
        }
    }

    // 2. Listen for Events from ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SyncContactsEvent.RequestContactPermission -> {
                    // Trigger the Android permission prompt
                    permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                }
                is SyncContactsEvent.NavigateNext -> {
                    // Trigger the navigation callback defined in your Nav Graph
                    onSyncContactsSuccess()
                }
                is SyncContactsEvent.ShowError -> {
                    // Handle error (e.g., show a Toast)
                }
            }
        }
    }

    // 3. Show Rationale Dialog if needed
    if (showPermissionDialog) {
        PermissionRationaleDialog(
            title = "Contact Permission Required",
            description = "To help your network save on insurance and for you to earn on renewals, we need access to your contacts.",
            onDismiss = { showPermissionDialog = false }
        )
    }

    // 4. Render the UI
    SyncContactsScreen(
        onSyncClicked = {
            // Pass the action to the ViewModel
            viewModel.onAction(SyncContactsAction.OnSyncContactsClick)
        },
        modifier = modifier
    )
}