package com.policyboss.customer.feature.home.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.home.viewmodel.HomeViewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle


import com.policyboss.customer.feature.home.model.home.HomeAction
import com.policyboss.customer.feature.home.model.home.HomeUiEvent
import androidx.core.net.toUri


/*
the Route (The Traffic Controller)
The HomeRoute is responsible for bridging the gap between
 your HomeScreen (UI), your HomeViewModel (Business Logic),
 and your NavHost (Navigation).

Instead of passing viewModel::onAction directly,
intercept the actions in a when block right here.
 */

/*
============= General rule =============
HomeRoute handles:

✅ Navigation

Profile screen

Vault screen

Bosspedia screen

Privilege screen

HomeRoute should be a traffic controller, not a UI state owner

These are screen navigationsThese are screen navigations

=================================================================
ViewModel handles:

✅ UI state

    Loading
    Selected tab
    BottomSheet open
    BottomSheet close
    Selected policy
    API response


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Does user leave HomeScreen?

                YES
                 ↓
                HomeRoute

                NO
                 ↓
                ViewModel
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),

    // Pass a navigation callback if you need to route to a different screen entirely
    // Callbacks provided by your NavGraph

   // Note: . Pure UI navigation goes straight to your NavGraph callbacks.
    // Complex actions go to the ViewModel.
    onNavigateToProfile: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToBosspedia: () -> Unit,
    onNavigateToPrivilege: () -> Unit
   // onAssistanceClick: () -> Unit

) {
    // Safely collect state respecting the Android lifecycle
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 1. Declare the lifecycle owner HERE
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    // Listen for one-time events from the ViewModel (e.g., API success)
    LaunchedEffect(viewModel.uiEvent, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is HomeUiEvent.NavigateToPrivilegeSuccess -> onNavigateToPrivilege()

                    is HomeUiEvent.ShowSnackbar -> { /* Handle Snackbar */ }

                    is HomeUiEvent.OpenDialer -> {
                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            "tel:${event.phoneNumber}".toUri()
                        )

                        context.startActivity(intent)


                    }
                    else -> {

                    }
                }
            }
        }
    }

        HomeScreen(
        modifier = modifier,
        contentPadding = contentPadding, // Pass this down so your grid doesn't underlap the system bars
        uiState = uiState,
        onAction = { action ->
            // THE TRAFFIC CONTROLLER
            when (action) {
                // 1. Intercept pure navigation actions directly
                //   // Pure navigation
                is HomeAction.OnProfileClick -> onNavigateToProfile()
                is HomeAction.OnViewAllVaultClick -> onNavigateToVault()
                is HomeAction.OnExploreBosspediaClick -> onNavigateToBosspedia()
               // is HomeAction.OnAssistanceClick -> onNavigateToBosspedia()
              //  is HomeAction.OnPrivilegeBannerClick -> onNavigateToPrivilege()

                // 2. Pass business-related actions to the ViewModel
                // Everything else

                else ->
                    viewModel.onAction(action)
            }
        }
    )
}