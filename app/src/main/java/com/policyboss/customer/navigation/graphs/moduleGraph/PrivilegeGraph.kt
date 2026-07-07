package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.privilege.ui.privilegeScreen.PrivilegeRoute
import com.policyboss.customer.feature.privilege.ui.privilegeScreen.privillegeStory.PrivilegeStoriesRoute
import com.policyboss.customer.feature.privilege.viewmodel.PrivilegeViewModel
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest

/*
AppNavigator
│
├── Auth
├── Splash
├── MainGraph
├── JoinPrivilege
└── Bosspedia

TabNavigator
│
├── HomeGraph
├── ClaimGraph
├── VaultGraph
└── PrivilegeGraph
 */
fun NavGraphBuilder.privilegeGraph(

  //  navController: NavHostController, // Global navigator (use only for logging out or going to root screens)
    appNavigator: AppNavigator,
    padding: PaddingValues  //<--- 1. This comes from the Scaffold
) {

    navigation<Dest.PrivilegeGraph>(

        startDestination = Dest.Privilege,

    ) {

        composable<Dest.Privilege> { backStackEntry ->
         // 1. Fetch the BackStackEntry of the parent graph
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
            }
            // 2. Inject the ViewModel scoped to the parent graph
            val viewModel: PrivilegeViewModel = hiltViewModel(parentEntry)

            PrivilegeRoute(
                modifier = Modifier,
                contentPadding = padding, // <--- 2. FIX: Pass the scaffold padding here! Not PaddingValues(0.dp)
                viewModel = viewModel,
                onNavigateToStories = {
                    appNavigator.navigateTo(Dest.PrivilegeStories)
                    // 🚀 FIX: Use navController here!
                   // navController.navigate(Dest.PrivilegeStories)
                }
            )
        }

        composable<Dest.PrivilegeStories> { backStackEntry ->

            // 3. Fetch the EXACT SAME parent entry

            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
            }

            // 4. Hilt will return the existing instance, keeping ExoPlayer alive and in sync
            val viewModel: PrivilegeViewModel =
                hiltViewModel(parentEntry)

            PrivilegeStoriesRoute(
                viewModel = viewModel,
                onCollapseClick = {
                    // 🚀 FIX: Use navController here!
                    appNavigator.navigateBack()
                },
                onCloseClick = {
                    appNavigator.navigateBack()
                }
            )
        }


    }
}