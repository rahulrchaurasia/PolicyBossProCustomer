package com.policyboss.customer.navigation






import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import kotlinx.serialization.Serializable

@Serializable
sealed class Dest {

    // =====================================
    // ROOT
    // =====================================


    // =====================================
    // GRAPHS : 3 Is Main Node
    // =====================================


    @Serializable
    data object CustomSplash : Dest()

    @Serializable data object AuthGraph : Dest()
    @Serializable data object MainGraph : Dest()

// Bottom Graphs

    @Serializable
    data object HomeGraph : Dest()

    @Serializable
    data object ClaimGraph : Dest()
    @Serializable

    data object VaultGraph : Dest()



    @Serializable
    data object PrivilegeGraph : Dest()

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
    // region Bottom Navigation
    @Serializable
    data object Home : Dest()

    @Serializable
    data object ClaimSupport : Dest()

    @Serializable
    data object PolicyVault : Dest()



    @Serializable
    data object Privilege : Dest()
    //endregion





    @Serializable
    data object JoinPrivilege : Dest()


}

