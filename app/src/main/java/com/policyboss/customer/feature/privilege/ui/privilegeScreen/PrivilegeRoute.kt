package com.policyboss.customer.feature.privilege.ui.privilegeScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.customer.feature.privilege.privillageState.PrivilegeUiEvent
import com.policyboss.customer.feature.privilege.viewmodel.PrivilegeViewModel

@Composable
fun PrivilegeRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from MainScreen Scaffold
    viewModel: PrivilegeViewModel = hiltViewModel(),
    onNavigateToStories: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    // 2. Pause video when user leaves the tab or backgrounds the app
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE || event == Lifecycle.Event.ON_STOP) {
                viewModel.player.pause()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Listen for one-time events
    LaunchedEffect(viewModel.uiEvent, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                   is PrivilegeUiEvent.NavigateToFullScreenVideo -> {
                       // 3. Trigger the navigation to your full screen route!
                       onNavigateToStories()
                   }
                    is PrivilegeUiEvent.ShowSnackbar -> {}
                }
            }
        }
    }



    // Render the stateless screen
    PrivilegeScreen(
        modifier = modifier,
        contentPadding = contentPadding, // Pass the padding down!
        uiState = uiState,

        // 1. Pass the injected player from the ViewModel
        exoPlayer = viewModel.player,

        // 2. Delegate all actions directly to the ViewModel
        onAction = viewModel::onAction
    )
}