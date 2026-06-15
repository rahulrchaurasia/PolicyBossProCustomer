package com.policyboss.customer.core// Local NavController Provider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No NavController found!")
}