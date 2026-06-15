package com.policyboss.customer.navigation

import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource


/*

 >>> 🧠 Golden Rule (FINAL)
✅ Final clarity (very short & exact)
1. startDestination

👉 MUST be String

startDestination = Dest.Home.route()   // ✅ correct
2. composable

👉 Type-safe is PERFECT

composable<Dest.Home> { }   // ✅ correct
3. navigate

👉 Type-safe

navController.navigate(Dest.Home)   // ✅ correct
🧠 One-line rule
StartDestination = String (.route())
Everything else = Type-safe (Dest.X)
 */




import kotlinx.serialization.Serializable

@Serializable
sealed class Dest {

    // =====================================
    // ROOT
    // =====================================

    @Serializable
    data object CustomSplash : Dest()


    // =====================================
    // GRAPHS
    // =====================================

    @Serializable data object AuthGraph : Dest()
    @Serializable data object MainGraph : Dest()

    // =====================================
    // AUTH DESTINATIONS
    // =====================================

    @Serializable
    data object Welcome : Dest()

    @Serializable
    data object Login : Dest()

    @Serializable
    data object Register : Dest()

    @Serializable
    data class VerifyAccount(
        val fullName: String,
        val mobileNumber: String,
        val source: VerifyOtpSource
    ) : Dest()

    // =====================================
    // MAIN APP DESTINATIONS
    // =====================================

    @Serializable
    data object MainScreen : Dest()

    // --- Standalone Screens (Accessible from Home, but not bottom tabs) ---

    @Serializable
    data object Profile : Dest()

    @Serializable
    data object Bosspedia : Dest()

    @Serializable
    data object Vault : Dest()

    // --- Bottom Navigation Destinations Tabs ---

    @Serializable
    data object Home : Dest()

    @Serializable
    data object ClaimSupport : Dest()

    @Serializable
    data object PolicyVault : Dest()


    @Serializable
    data object Privilege : Dest()
}
//
//@Serializable
//sealed class Dest {
//
//
//    // =====================================
//    // ROOT
//    // =====================================
//
//    @Serializable
//    data object CustomSplash : Dest()
//
//
//    // =====================================
//    // GRAPHS
//    // =====================================
//
//
//    // Graphs
//    @Serializable data object AuthGraph : Dest()
//    @Serializable data object MainGraph : Dest()
//
//    //********************************************************/
//
//    // Auth Destinations
//    @Serializable
//    data object Welcome : Dest()
//
//    @Serializable
//    data object Login : Dest()
//
//    @Serializable
//    data object Register : Dest()
//
//    @Serializable
//    data class VerifyAccount(
//
//        val fullName: String,
//
//        val mobileNumber: String,
//
//        val source: VerifyOtpSource
//
//    ) : Dest()
//
//    // Main App Destinations
//
////    @Serializable
////    data object MainHome : Dest()
//
//    @Serializable
//    data object MainScreen : Dest()
//
//
//     //********* Main Screen Bottom Navigation Tab
//
//    // Bottom Navigation  Destinations Tabs
//    @Serializable
//    data object Home : Dest()
//
//    @Serializable
//    data object ClaimSupport : Dest()
//
//    @Serializable
//    data object PolicyVault : Dest()
//
//    @Serializable
//    data object Privilege : Dest()
//
//
//
//}
