package com.policyboss.customer.feature.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.homeState.HomeAction
import com.policyboss.customer.feature.home.model.homeState.HomeUiState


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.ui.graphics.Color

import com.policyboss.customer.feature.home.component.home.vaultSection.PolicyVaultSection
import com.policyboss.customer.feature.home.component.home.footer.BosspediaSection
import com.policyboss.customer.feature.home.component.home.footer.FooterTrustSection


import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.component.home.assistanceSection.AssistanceSection

import com.policyboss.customer.feature.home.component.home.earningOpportunitySection.EarningOpportunitySection
import com.policyboss.customer.ui.components.divider.SectionDivider

import com.policyboss.customer.feature.home.component.home.heroSection.HeroSection
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.PolicyProtectedBottomSheet


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.lazy.LazyColumn
import com.policyboss.customer.feature.home.component.home.QuickActionsGrid
import com.policyboss.customer.feature.home.component.home.currentPolicySection.PolicyCategoryGrid
import com.policyboss.customer.feature.home.component.home.footer.TrustedPartnersSection.TrustedPartnersSection
import com.policyboss.customer.feature.home.component.home.videoSection.VideoSliderSection


//The golden rule of UDF is: State flows down, Actions flow up.

/*

Architecture
HomeScreen
│
└── LazyVerticalGrid (ONLY ONE)
    │
    ├── HeroSection (Full Width)
    │      ├── Gradient
    │      ├── Header
    │      ├── Banner Pager
    │      ├── Clouds
    │      └── White Curve
    │
    ├── Quick Action Card
    ├── Quick Action Card
    ├── Quick Action Card
    ├── Quick Action Card
    │
    ├── Earning Opportunity
    │
    ├── Curated Policies Header
    ├── Policy Card
    ├── Policy Card
    │
    ├── Divider
    ├── Vault
    ├── Bosspedia
    └── Footer
Why?

Your screen naturally contains:

Full width sections
2-column card sections
Full width sections again

This is exactly what LazyVerticalGrid with GridItemSpan(maxLineSpan) was designed for.
 */



private val HeroBottomTransition = 320.dp
private val CurveOverlap = 20.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit
) {

    // Wrap the entire screen in a Box so you can layer UI elements

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(

            modifier = modifier
                .fillMaxSize()
                .background(Color.White),

            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding() + 32.dp,

//            start = 16.dp, // Assuming you want side padding on the grid
//            end = 16.dp

            ),
            // horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        )
        {

            //region Merge HeroWithQuickActions

//            item {
//
//                HeroWithQuickActions(
//
//                    userName = uiState.userName,
//
//                    initials = uiState.userInitials,
//
//                    promoBanners = uiState.promoBanners,
//
//                    quickActions = uiState.quickActions,
//
//
//
//                    onProfileClick = {
//
//                        onAction(
//
//                            HomeAction.OnProfileClick
//
//                        )
//
//                    },
//
//
//
//                    onBannerClick = { banner ->
//
//                        onAction(
//
//                            HomeAction.OnPromoBannerClick(
//
//                                banner.id
//
//                            )
//
//                        )
//
//                    },
//
//
//
//                    onQuickActionClick = {
//
//                        onAction(
//
//                            HomeAction.OnQuickActionClick(it)
//
//                        )
//
//                    }
//
//                )
//            }

            //endregion

            item {

                // =====================================================
                // 1. HERO / HEADER SECTION (Full Width)
                // =====================================================

                // =====================================================
                // POLICIES HEADER
                // =====================================================

                //region POLICIES HEADER

                 HeroSection(
                    userName = uiState.userName,
                    initials = uiState.userInitials,
                    promoBanners = uiState.promoBanners,
                    onProfileClick = {
                        onAction(HomeAction.OnProfileClick)
                    },
                    onBannerClick = { banner ->
                        onAction(
                            HomeAction.OnPromoBannerClick(
                                banner.id
                            )
                        )
                    }
                )

                //endregion


            }


            item {

                // =====================================================
                // 2. QUICK ACTIONS GRID (Half Width)
                // =====================================================

                QuickActionsGrid(
                    actions = uiState.quickActions,
                    onClick = {
                        onAction(HomeAction.OnQuickActionClick(it))
                    },
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .offset(y = (-42).dp)
                )
            }


            item {

                // =====================================================
                // EARNING SECTION
                // =====================================================

                EarningOpportunitySection(
                    banners = uiState.earningBanners,
                    onJoinPrivilegeClick = {
                        onAction(HomeAction.OnPrivilegeBannerClick)
                    },
                    onBannerClick = { bannerId ->
                        onAction(
                            HomeAction.OnEarningBannerClick(
                                bannerId
                            )
                        )
                    },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }


            item {
                AssistanceSection(

                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),

                    onClick = {
                        onAction(
                            HomeAction.OnAssistanceClick
                        )
                    }
                )
            }


            item {
                HorizontalDivider(
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    ),
                    color = Color(0xFFEAECF0)
                )
            }

            item {

                // =====================================================
                // VAULT
                // =====================================================

                val selectedPolicies = uiState.vaultPolicies.filter {
                    it.tabId == uiState.selectedVaultTab
                }

                PolicyVaultSection(

                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),

                    selectedTab = uiState.selectedVaultTab,
                    policies = selectedPolicies,


                    onTabSelected = {

                        onAction(

                            HomeAction.OnVaultTabSelected(it)
                        )
                    },

                    onViewAllClick = {

                        onAction(

                            HomeAction.OnViewAllVaultClick
                        )
                    },

                    onRenewClick = { policy ->

                        onAction(

                            HomeAction
                                .OnShowPolicyBottomSheetClick(policy)
                        )
                    },

                    onViewDetailsClick = { policy ->

                        //pending Action of View Details
                    }
                )
            }

            item {
                SectionDivider()
            }

            item {
                Text(
                    text = "Curated Policies - Just for you",
                    style = MaterialTheme.typography.headlineMedium,

                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    )
                )
            }

            item {

                PolicyCategoryGrid(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    policies = uiState.curatedPolicies,

                    onPolicyClick = {

                        onAction(

                            HomeAction.OnPolicyCategoryClick(it)
                        )
                    }
                )
            }

            item {
                // =====================================================
                // BOSSPEDIA
                // =====================================================

                //region BOSSPEDIA
                BosspediaSection(
                    modifier = Modifier.padding(horizontal = 24.dp),

                    // 1. Pass the data from uiState
                    stories = uiState.bosspediaStories,
                    articles = uiState.bosspediaArticles,

                    // 2. Map the actions
                    onExploreMoreClick = {
                        onAction(HomeAction.OnExploreBosspediaClick)
                    },
                    onArticleClick = { articleId ->
                       // onAction(HomeAction.OnBosspediaArticleClick(articleId))
                    },
                    onStoryClick = { storyId ->
                       // onAction(HomeAction.OnBosspediaStoryClick(storyId))
                    }
                )
                //endregion
            }

            // =====================================================
            // VIDEOS SECTION (Placed right before the footer)
            // =====================================================
            item {

                // Only render the section if there are videos available
                if (uiState.videos.isNotEmpty()) {
                    VideoSliderSection(

                        modifier = Modifier.padding(horizontal = 24.dp),
                        videos = uiState.videos,

                        onViewMoreClick = {
                            onAction(HomeAction.OnVideoViewMoreClick)
                        },

                        onVideoClick = { video ->
                            // Passing only the ID back to the ViewModel!
                            onAction(HomeAction.OnVideoClick(video.id))
                        }
                    )
                }
            }

            // =====================================================
            // FOOTER
            // =====================================================

            item {

                FooterTrustSection(  modifier = Modifier.padding(horizontal = 24.dp))
            }

            // =====================================================
            // FOOTER TRUSTED PARTNERS
            // =====================================================
            item {
                TrustedPartnersSection(
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

        }

        // =====================================================
        //  OVERLAYS / BOTTOM SHEETS (Drawn last, on top of the grid)
        // =====================================================
        //if selectedVaultPolicy  exists → sheet should open
        //region OVERLAYS / BOTTOM SHEETS
        uiState.selectedVaultPolicy?.let { policy ->
            PolicyProtectedBottomSheet(
                policy = policy,
                onDismiss = {
                    onAction(HomeAction.OnDismissPolicyBottomSheet)
                }
            )
        }
        //endregion

    }


}




//
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeScreenPreview() {
    // Create dummy state for the preview
    val dummyState = HomeUiState(
        userName = "Alex",
        userInitials = "A",
        curatedPolicies = AppDummyData.curatedPolicies,
    )

    MaterialTheme @Composable {
        HomeScreen(
            uiState = dummyState,
            onAction = { action ->
                // Do nothing in preview, or log it if testing interactively
                println("Action triggered: $action")
            }
        )
    }
}



