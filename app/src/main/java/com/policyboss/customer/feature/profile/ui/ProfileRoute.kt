package com.policyboss.customer.feature.profile.ui




import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.customer.feature.profile.model.ProfileEvent
import com.policyboss.customer.feature.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from AppRoot Scaffold
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onCloseClick: () -> Unit // 👈 1. Add the callback parameter here
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 1. Listen for One-Time Events (like Navigation after successful logout)
    LaunchedEffect(viewModel.event, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            viewModel.event.collectLatest { event ->
                when (event) {
                    is ProfileEvent.NavigateToLogin -> {

                        //Mark : Logout :--> Then  navigate to Login
                        // Trigger the navigation callback back to the Graph!
                        onNavigateToLogin()
                    }
                }
            }

        }
    }

    // 2. Render the Stateless UI
    ProfileScreen(
        modifier = modifier,
        contentPadding = contentPadding,
        uiState = uiState,
        onLogoutClick = {
            // Tell ViewModel the user clicked Logout
            viewModel.logout()
        },
        onCloseClick = onCloseClick // 👈 2. Pass it directly to the UI! No ViewModel needed.
    )
}