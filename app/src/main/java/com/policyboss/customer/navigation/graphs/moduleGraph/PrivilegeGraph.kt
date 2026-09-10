package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.ui.PrivilegeEmailPanRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.viewmodel.PrivilegeEmailPanViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.PrivilegeRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.privillegeStory.PrivilegeStoriesRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.viewmodel.PrivilegeViewModel
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
      // ==========================================
        // 1. PRIVILEGE MAIN TAB SCREEN
        // ==========================================
        composable<Dest.Privilege> { backStackEntry ->
         // 1. Fetch the BackStackEntry of the parent graph
          //  Get the SHARED Parent ViewModel (Scoped to Dest.PrivilegeGraph)
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
                },
                onNavigateToEmailPan = {
                    appNavigator.navigateTo(Dest.PrivilegeEmailPan)
                }
            )
        }

        // ==========================================
        // 2. PRIVILEGE STORIES (Full Screen View)
        // ==========================================
        composable<Dest.PrivilegeStories>(
            enterTransition = { NavigationAnimations.slideInRight },
        exitTransition = { NavigationAnimations.slideOutLeft },
        popEnterTransition = { NavigationAnimations.slideInLeft },
        popExitTransition = { NavigationAnimations.slideOutRight }
        )
        { backStackEntry ->

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


      // ==========================================
        // 3. PRIVILEGE JOURNEY: EMAIL & PAN SCREEN
        // ==========================================
        composable<Dest.PrivilegeEmailPan>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed

        ) { backStackEntry ->

            // 3. Fetch the EXACT SAME parent entry


            // 1. Scope ViewModel to the Journey Graph so next screens (OTP/PAN confirm) can share state
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
            }

            // 4. Hilt will return the existing instance, keeping ExoPlayer alive and in sync
            val viewModel: PrivilegeEmailPanViewModel =
                hiltViewModel(parentEntry)




            PrivilegeEmailPanRoute(
                viewModel = viewModel,
                onNavigateBack = {
                    appNavigator.navigateBack()
                },
                onNavigateNext = {
                    // TODO: Navigate to the next step in the journey (e.g., OTP Verification screen)
                    // appNavigator.navigateTo(Dest.PrivilegeOtpVerification)
                },
                modifier = Modifier
            )
        }


//        composable<Dest.PrivilegeVerification> { backStackEntry ->
//
//            // 1. Scope ViewModel to the Journey Graph so next screens (OTP/PAN confirm) can share state
//            val parentEntry = remember(backStackEntry) {
//                appNavigator.getBackStackEntry<Dest.PrivilegeAccountJourneyGraph>()
//            }
//
//            val viewModel: PrivilegeVerificationViewModel = hiltViewModel(parentEntry)
//
//            PrivilegeVerificationRoute(
//                viewModel = viewModel,
//                onNavigateBack = {
//                    appNavigator.navigateBack()
//                },
//                onNavigateNext = {
//                    // TODO: Navigate to the next step in the journey (e.g., OTP Verification screen)
//                    // appNavigator.navigateTo(Dest.PrivilegeOtpVerification)
//                },
//                modifier = Modifier
//            )
//        }

    }
}