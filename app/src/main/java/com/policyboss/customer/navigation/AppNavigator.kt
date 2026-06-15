package com.policyboss.customer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState

// ✅ Note: Centralized Navigation Logic - 100% Type-Safe version
class AppNavigator(private val navController: NavController) {


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

    fun navigateToTab(destination: Any) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

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

    // =====================================
    // MAIN
    // =====================================

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



}