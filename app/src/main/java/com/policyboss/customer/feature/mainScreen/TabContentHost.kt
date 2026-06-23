package com.policyboss.customer.feature.mainScreen

import com.policyboss.customer.navigation.AppNavigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.policyboss.customer.feature.home.ui.HomeRoute
import com.policyboss.customer.feature.home.ui.tabScreen.CartScreen
import com.policyboss.customer.feature.home.ui.tabScreen.ProfileScreen
import com.policyboss.customer.feature.home.ui.tabScreen.WishListScreen
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
    globalActions: AppNavigator,
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
                    globalActions.navigateTo(Dest.Profile) // Adjust based on your AppNavigator setup
                },
                onNavigateToVault = {
                    globalActions.navigateTo(Dest.Vault)
                },
                onNavigateToBosspedia = {
                    globalActions.navigateTo(Dest.Bosspedia)
                },
                onNavigateToPrivilege = {
                    globalActions.navigateTo( Dest.JoinPrivilege)
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
         * PROFILE
         * =================================================
         */
        Dest.Privilege -> {

            ProfileScreen(
                modifier = Modifier,
                onLogout = {

                    globalActions.navigateToLoginAndClear()
                }
            )
        }

        else -> Unit
    }
}
