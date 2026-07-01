package com.policyboss.customer.feature.mainScreen.notUsed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.policyboss.customer.feature.home.ui.HomeRoute
import com.policyboss.customer.feature.home.ui.tabScreen.CartScreen
import com.policyboss.customer.feature.home.ui.tabScreen.WishListScreen
import com.policyboss.customer.feature.privilege.ui.PrivilegeRoute
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest

/**
 * =========================================================
 * TAB CONTENT HOST
 * =========================================================
 *
 * RESPONSIBILITY:
 * -> OPEN CORRECT SCREEN
 * -> APPLY BOTTOM PADDING
 *
 */

@Composable
fun TabContentHost(
    selectedTab: Dest,
    appNavigator: AppNavigator,
    padding: PaddingValues
) {

    val contentPadding = PaddingValues(
        bottom = padding.calculateBottomPadding()
    )


    when (selectedTab) {

        /**
         * =================================================
         * HOME
         * =================================================
         */
        Dest.Home -> {

            /**
             * IMPORTANT:
             *
             * USE ROUTE HERE
             * NOT SCREEN DIRECTLY
             *
             * ROUTE:
             * -> VIEWMODEL
             * -> STATE
             * -> EVENTS
             *
             * SCREEN:
             * -> PURE UI ONLY
             *
             */
            HomeRoute(
                modifier = Modifier,
                contentPadding = contentPadding,

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

        /**
         * =================================================
         * WISHLIST
         * =================================================
         */
        Dest.ClaimSupport -> {

            WishListScreen(
                modifier = Modifier,

            )
        }

        /**
         * =================================================
         * CART
         * =================================================
         */
        Dest.PolicyVault -> {

            CartScreen(
                modifier = Modifier,

            )
        }

        /**
         * =================================================
         * Privilege
         * =================================================
         */
        Dest.Privilege -> {

            PrivilegeRoute(
                contentPadding = padding,
                onNavigateToQuiz = {

                    appNavigator.navigateTo(Dest.JoinPrivilege)
                }
            )
        }

        else -> Unit
    }
}
