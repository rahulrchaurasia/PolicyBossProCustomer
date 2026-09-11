package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.ui.PrivilegeEmailPanRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.viewmodel.PrivilegeEmailPanViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.ui.PrivilegeVerifyEmailRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyEmail.viemodel.PrivilegeVerifyEmailViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.ui.VerifyPanRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.viewmodel.VerifyPanViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.PrivilegeRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.privillegeStory.PrivilegeStoriesRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.viewmodel.PrivilegeViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.ui.SyncContactsRoute
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.viemodel.SyncContactsViewModel
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.ui.SyncContactsProcessingRouter
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.viemodel.SyncContactsProcessingViewModel
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

    )
    {
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

        )
        { backStackEntry ->


            // 🚨 FIX: Scope to backStackEntry instead of parentEntry!
            // This ensures a fresh ViewModel (empty fields, no errors) every time you open
            val viewModel: PrivilegeEmailPanViewModel =
                hiltViewModel(backStackEntry)

            PrivilegeEmailPanRoute(
                viewModel = viewModel,
                onNavigateBack = {
                    appNavigator.navigateBack()
                },
                onNavigateNext = {
                    // 👇 Extract current state values and pass them forward
                    val currentState = viewModel.uiState.value

                    appNavigator.navigateTo(
                        Dest.PrivilegeVerifyEmail(
                            email = currentState.email,
                            panNumber = currentState.panNumber
                        )
                    )
                },
                modifier = Modifier
            )


        }

        // ==========================================
        // 4. PRIVILEGE JOURNEY: Verify EMAIL  SCREEN
        // ==========================================
        composable<Dest.PrivilegeVerifyEmail>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed

        )
        { backStackEntry ->


            val viewModel: PrivilegeVerifyEmailViewModel = hiltViewModel(backStackEntry)

            // 👇 1. Extract the arguments safely using Compose Navigation 2.8+ syntax
            val args = backStackEntry.toRoute<Dest.PrivilegeVerifyEmail>()
            val passedEmail = args.email
            val passedPan = args.panNumber

            PrivilegeVerifyEmailRoute(
                mobileNumber = passedEmail,
                viewModel = viewModel,
                onNavigateBack = {
                    appNavigator.navigateBack()
                },
                onNavigateNext = {
                    // TODO: Navigate to the next step in the journey (e.g., OTP Verification screen)
                    // appNavigator.navigateTo(Dest.PrivilegeVerifyPan)

                    appNavigator.navigateTo(Dest.PrivilegeVerifyPan(panNumber = passedPan)) {
                        // 👇 This removes the current screen (VerifyEmail) from the history

                        popUpTo<Dest.PrivilegeVerifyEmail> {

                            inclusive = true
                        }
                    }


                },
                modifier = Modifier
            )
        }


        // ==========================================
        // 4. PRIVILEGE JOURNEY: Verify PAN  SCREEN
        // ==========================================
        composable<Dest.PrivilegeVerifyPan>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed

        )
        { backStackEntry ->

            // 🚨 CRITICAL FIX: Do NOT use `parentEntry` here!
            // To read Type-Safe arguments from SavedStateHandle, the ViewModel MUST be
            // scoped to the current screen's `backStackEntry`.
            val viewModel: VerifyPanViewModel = hiltViewModel(backStackEntry)

            VerifyPanRoute(
                onNavigateNext = {
                    appNavigator.navigateTo(Dest.SyncContact)
                },
                onNavigateBack = {
                    appNavigator.navigateBack()
                },
                onCloseJourney = {
                    appNavigator.navigateBack()
                },
                viewModel = viewModel,
                modifier = Modifier
            )
        }


        // ==========================================
        // Sync Contact Started ...
        // ==========================================

        composable<Dest.SyncContact>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed

        )
        { backStackEntry ->

            // 3. Fetch the EXACT SAME parent entry


            // 1. Scope ViewModel to the Journey Graph so next screens (OTP/PAN confirm) can share state
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
            }

            // 4. Hilt will return the existing instance, keeping ExoPlayer alive and in sync
            val viewModel: SyncContactsViewModel =
                hiltViewModel(parentEntry)

            SyncContactsRoute(
                viewModel = viewModel,

                onSyncContactsSuccess = {
                    // Navigate to the processing screen!
                    appNavigator.navigateTo(Dest.SyncContactProcess)
                },
                modifier = Modifier
            )

        }


//        // ==========================================
//        // 2. Sync Contact Processing ...
//        // ==========================================

        composable<Dest.SyncContactProcess>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft },
            popEnterTransition = { NavigationAnimations.slideInLeft },
            popExitTransition = { NavigationAnimations.slideOutRight }
        ) {  backStackEntry ->
            // Note: We don't necessarily need to scope this ViewModel to the parent graph
            // unless next screens need this exact state. A standard ViewModel is usually fine here.

            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
            }

            // 4. Hilt will return the existing instance, keeping ExoPlayer alive and in sync
            val viewModel: SyncContactsProcessingViewModel =
                hiltViewModel(parentEntry)


            SyncContactsProcessingRouter(
                viewModel = viewModel,
                onNavigateToHome = {



                    // ✅ USING YOUR APP NAVIGATOR!
                    // This safely pops all the Sync screens off the stack
                    // and returns the user to the root Main Dashboard.
                    appNavigator.navigateBackToMainGraph()
                },
                modifier = Modifier
            )
        }


    }
}