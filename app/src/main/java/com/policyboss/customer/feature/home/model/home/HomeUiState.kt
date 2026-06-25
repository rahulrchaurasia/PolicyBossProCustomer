package com.policyboss.customer.feature.home.model.home

import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaArticle
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaStory
import com.policyboss.customer.feature.home.model.policy.CuratedPolicy
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.feature.home.model.video.VideoModel

/*
        User Click
           ↓
        HomeScreen
           ↓
        onAction(HomeAction)
           ↓
        HomeRoute
           ↓
        ViewModel (if business logic/API required)
           ↓
        UiState OR UiEvent
           ↓
        HomeRoute
           ↓
        Navigation / Snackbar
 */
// The UI State for the Home Screen ONLY

//Mark : HomeViewModel decides which experience to show.
data class HomeUiState(

    val experience: HomeExperience = HomeExperience.EXPLORE,
    val isLoading: Boolean = false,
    val userName: String = "Rahul",
    val userInitials: String = "RC",

    val rmMobileNo: String = "9224624999",


    // Lists driving the UI
    val promoBanners: List<PromoBanner> = emptyList(),
    val quickActions: List<QuickAction> = emptyList(),
    val earningBanners: List<EarningBanner> = emptyList(),
    val curatedPolicies: List<CuratedPolicy> = emptyList(),


    //region Bosspedia
    val bosspediaStories:  List<BosspediaStory> = emptyList(),
    val bosspediaArticles:  List<BosspediaArticle> = emptyList(),
    //endregion

    //Mark : For Video
    val videos: List<VideoModel> = emptyList(),

   // Mark: PolicyBottomSheet
    val showPolicyBottomSheet: Boolean = false,  // for   BottomSheet


    // Mark: vaultPolicies
    //region vaultPolicies
    val selectedVaultTab: Int = 0,
    val vaultPolicies: List<VaultPolicy> = emptyList(),
    val selectedVaultPolicy: VaultPolicy? = null,
    //endregion






    )