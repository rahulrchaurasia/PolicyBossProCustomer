package com.policyboss.customer.navigation






import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType

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

  //  Bottom Graphs

    //region  Graphs
    @Serializable
    data object HomeGraph : Dest()

    @Serializable
    data object ClaimGraph : Dest()
    @Serializable

    data object VaultGraph : Dest()



    @Serializable
    data object PrivilegeGraph : Dest()

    //endregion

    // =====================================
    // AUTH DESTINATIONS
    // =====================================

     // region AUTH DESTINATIONS
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

    //endregion


    // =====================================
    // MAIN APP DESTINATIONS
    // =====================================

    // region MAIN APP DESTINATIONS
    @Serializable
    data object MainScreen : Dest()

    // --- Standalone Screens (Accessible from Home, but not bottom tabs) ---

    @Serializable
    data object Profile : Dest()

    @Serializable
    data object Bosspedia : Dest()

    @Serializable
    data object Vault : Dest()

    //endregion

    // --- Bottom Navigation Destinations Tabs ---
    // region Bottom Navigation
    @Serializable
    data object Home : Dest()

    @Serializable
    data object ClaimSupport : Dest()

    @Serializable
    data object PolicyVault : Dest()

    @Serializable
    data class AddManualPolicy(

        val policyType: AddPolicyType
    ) : Dest()




    @Serializable
    data object Privilege : Dest()
    //endregion

    // region  Privilege Journey
    @Serializable
    data object JoinPrivilege : Dest()


    @Serializable
    data object PrivilegeStories : Dest()

    @Serializable
    data object PrivilegeEmailPan : Dest() // Formerly EmailPan


    @Serializable
    data class PrivilegeVerifyEmail(
        val email: String,
        val panNumber: String
    )


    @Serializable

    data class PrivilegeVerifyPan(val panNumber: String) : Dest()


    //endregion

    // 🚀 NEW: Add this specifically for the Home "View All" flow
    // region  HOME Journey
    @Serializable
    data object HomeAllVault : Dest()

    //endregion

    // region  Claim Journey

    // 🚀 NEW: Target Destinations taking an argument



    @Serializable
    data class ClaimGuide(val productType: AddPolicyType) : Dest()


    @Serializable
    data class FileClaim(val productType: AddPolicyType) : Dest()

    @Serializable
    object AccidentDetails : Dest()


    @Serializable
    object ThirdPartyDetails : Dest()

    @Serializable
    object DamagePhotos : Dest()

    @Serializable
    object PoliceReport : Dest()

    @Serializable
    object DriversLicense : Dest()




    //endregion


    }

