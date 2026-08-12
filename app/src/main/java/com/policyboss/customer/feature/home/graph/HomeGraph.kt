package com.policyboss.customer.feature.home.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.home.ui.main.homeScreen.HomeRoute
import com.policyboss.customer.feature.home.ui.main.homeVault.HomeVaultRoute
import com.policyboss.customer.feature.profile.ui.ProfileRoute
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest

/*
What is toRoute()?

toRoute() is an extension function provided by Navigation Compose's
type-safe navigation.

Its job is:
Read the navigation arguments from the NavBackStackEntry and convert
them back into your destination object.
 */

fun NavGraphBuilder.homeGraph(


    appNavigator: AppNavigator,
    padding: PaddingValues

) {

    navigation<Dest.HomeGraph>(
        startDestination = Dest.Home
    ) {

        composable<Dest.Home> {

            HomeRoute(
                modifier = Modifier,
               // contentPadding = PaddingValues(0.dp),
                contentPadding = padding, // <--- 2. FIX: Pass the scaffold padding here! Not PaddingValues(0.dp)
                // --- PASS THE NAVIGATION CALLBACKS HERE ---
                onNavigateToProfile = {
                    appNavigator.navigateTo(Dest.Profile) // Adjust based on your AppNavigator setup
                },

//                onNavigateToVault = {
//                    appNavigator.navigateTo(Dest.Vault)
//                },
                onNavigateToVault = {
                    appNavigator.navigateTo(Dest.HomeAllVault)
                   },

                onNavigateToBosspedia = {
                    appNavigator.navigateTo(Dest.Bosspedia)
                },
                onNavigateToPrivilege = {
                    appNavigator.navigateTo( Dest.JoinPrivilege)
                }
            )
        }

//        composable<Dest.Vault> {
//            // Render your VaultRoute/VaultScreen here
//            Text("Vault Screen PlaceHolder")
//        }
        // 🚀 NEW: Add the Home View All Screen
        composable<Dest.HomeAllVault>(

            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }

        ) {
            HomeVaultRoute(
                contentPadding = padding,
                onBackClick = { appNavigator.navigateBack() },
                onRenewClick = { policy ->
                    // Handle renew click
                },
                onViewDetailsClick = { policy ->
                    // Handle view details click
                }
            )
        }



        composable<Dest.Bosspedia> {
            // Render your BosspediaRoute/BosspediaScreen here
            Text("Bosspedia Screen PlaceHolder")
        }


        composable<Dest.Profile>(
            // 1. Moving FORWARD into Profile (Appears from Bottom to Top)
            enterTransition = { NavigationAnimations.slideInBottom },

            // When navigating DEEPER from Profile (Profile hides smoothly)
           // exitTransition = { NavigationAnimations.fadeOut },
            exitTransition = { NavigationAnimations.slideOutTop },
            // When returning TO Profile from a deeper screen (Profile reappears smoothly)
           // popEnterTransition = { NavigationAnimations.fadeIn },
            popEnterTransition = { NavigationAnimations.slideInTop },

            // 2. Moving BACKWARD out of Profile (Closes from Top to Bottom)
            popExitTransition = { NavigationAnimations.slideOutBottom }

        ) {
            ProfileRoute(
                // contentPadding = PaddingValues(0.dp), // or pass actual padding if needed
                contentPadding = padding,
                onNavigateToLogin = {
                    appNavigator.navigateToLoginAfterLogout()
                },

                onCloseClick = {
                    // 👈 3. Just trigger your normal back navigation!
                    appNavigator.navigateBack()
                }
            )
        }



    }

}