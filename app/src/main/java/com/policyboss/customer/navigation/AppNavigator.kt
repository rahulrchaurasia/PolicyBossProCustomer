package com.policyboss.customer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState

// ✅ Note: Centralized Navigation Logic - 100% Type-Safe version
class AppNavigator( val navController: NavController) {


    // =====================================
    // GENERIC
    // =====================================
    fun navigateTo(dest: Dest) {

        navController.navigate(dest)
    }

    // ─────────────────────────────────────
    // 1. BASIC NAVIGATION (Type-Safe Objects Only)
    // ─────────────────────────────────────

    // ✅ Use 'Any' to support both Objects and Data Classes
    fun navigateTo(destination: Any, navOptions: NavOptionsBuilder.() -> Unit = {}) {
        navController.navigate(destination, navOptions)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    // ─────────────────────────────────────
    // 2. TAB NAVIGATION (Industrial Standard)
    // ─────────────────────────────────────

    // ✅ FIXED: Explicitly popUpTo Dest.MainGraph so we don't accidentally pop back to Splash!
    fun navigateToTab(destination: Any) {
        navController.navigate(destination) {
            popUpTo<Dest.MainGraph> {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
//    fun navigateToTab(destination: Any) {
//        navController.navigate(destination) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }

    // ─────────────────────────────────────
    // 3. STACK CONTROL (Fixed to remove .route())
    // ─────────────────────────────────────

    // ✅ FIXED: Using the object directly for type-safety
    fun popBackToRoute(destination: Any, inclusive: Boolean = false) {
        navController.popBackStack(destination, inclusive)
    }

    // ✅ FIXED: Safe pop back with fallback using objects
    fun safePopBackToRoute(
        destination: Any,
        inclusive: Boolean = false,
        fallback: (() -> Unit)? = null
    ) {
        val success = navController.popBackStack(destination, inclusive)
        if (!success) {
            fallback?.invoke() ?: navigateBack()
        }
    }

    // ─────────────────────────────────────
    // 4. AUTH & MAIN FLOWS
    // ─────────────────────────────────────

    fun navigateToWelcome() {
        navController.navigate(Dest.Welcome) {
            // ✅ Use type-safe popUpTo instead of '0'
            popUpTo<Dest.CustomSplash> { inclusive = true }
        }
    }

    fun navigateToHome() {
        // ✅ Navigating to the Graph ID object
        navController.navigate(Dest.MainGraph) {
            popUpTo<Dest.CustomSplash> { inclusive = true }
        }
    }

    // Specialized Logic Helpers
    fun navigateToLogin() = navigateTo(Dest.Login)
    fun navigateToRegister() = navigateTo(Dest.Register)


    // 📁 AppNavigator.kt
    fun navigateToLoginAndClear() {
        navController.navigate(Dest.Login) {
            // ✅ This clears the entire backstack so the user starts fresh
            popUpTo(0) { inclusive = true }

            // Ensures we don't create multiple login screens if double-clicked
            launchSingleTop = true
        }
    }

    // 📁 AppNavigator.kt

    fun navigateToLoginAfterLogout() {
        // 1. Wipe out the entire MainGraph history and drop the user at Welcome
        navController.navigate(Dest.Welcome) {
            // popUpTo(0) means "clear absolutely everything in the backstack"
            popUpTo(0) { inclusive = true }

            // 2. Prevent double-instantiation if they double-click
            launchSingleTop = true
        }

        // 2. Instantly push the Login screen on top of Welcome
        navController.navigate(Dest.Login)
    }
    // =====================================
    // MAIN
    // =====================================

    // ✅ Use this to gracefully close out of sub-flows (like Claim Filing)
    // and return the user to the existing Main Dashboard state.
    fun navigateBackToMainGraph() {
        popBackToRoute(Dest.MainGraph, inclusive = false)
    }

    fun navigateToMainAndClear() {

        navController.navigate(Dest.MainGraph) {

            popUpTo(0) {
                inclusive = true
            }

            launchSingleTop = true
        }
    }

    // ─────────────────────────────────────
    // 5. UI STATE HELPERS
    // ─────────────────────────────────────
    @Composable
    fun currentRouteAsState(): String? {
        val entry by navController.currentBackStackEntryAsState()
        return entry?.destination?.route
    }


    @Composable
    fun currentDestination(): NavDestination? {
        val entry by navController.currentBackStackEntryAsState()
        return entry?.destination
    }

    // ✅ Made public so privilegeGraph can use it for Hilt parent-scoping without needing the raw navController!
    inline fun <reified T : Any> getBackStackEntry(): NavBackStackEntry {
        return navController.getBackStackEntry<T>()
    }
}