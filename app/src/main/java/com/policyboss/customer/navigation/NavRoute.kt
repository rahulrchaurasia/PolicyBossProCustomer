package com.policyboss.customer.navigation

sealed class NavRoute(val route: String) {
    object Onboarding : NavRoute("onboarding")
    object Home : NavRoute("home")
}