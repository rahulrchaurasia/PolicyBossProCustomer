package com.policyboss.customer.navigation


//Not In Used
sealed class NavRoute(val route: String) {
    object Onboarding : NavRoute("onboarding")
    object Home : NavRoute("home")
}