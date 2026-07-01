package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.home.ui.HomeRoute
import com.policyboss.customer.feature.home.ui.tabScreen.ProfileScreen
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest


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
                contentPadding = padding,

                // --- PASS THE NAVIGATION CALLBACKS HERE ---
                onNavigateToProfile = {
                    appNavigator.navigateTo(Dest.Profile) // Adjust based on your AppNavigator setup
                },

                onNavigateToVault = {
                    appNavigator.navigateTo(Dest.Vault)
                },
                onNavigateToBosspedia = {
                    appNavigator.navigateTo(Dest.Bosspedia)
                },
                onNavigateToPrivilege = {
                    appNavigator.navigateTo( Dest.JoinPrivilege)
                }
            )
        }

        composable<Dest.Profile> {

            ProfileScreen()

        }

//        composable<Dest.Bosspedia> {
//
//            BosspediaScreen()
//
//        }


    }

}