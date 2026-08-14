package com.policyboss.customer.feature.home.ui.main.homeScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.component.home.QuickActionsGrid
import com.policyboss.customer.feature.home.component.home.assistanceSection.AssistanceSection
import com.policyboss.customer.feature.home.component.home.currentPolicySection.PolicyCategoryGrid
import com.policyboss.customer.feature.home.component.home.earningOpportunitySection.EarningOpportunitySection
import com.policyboss.customer.feature.home.component.home.footer.BosspediaSection
import com.policyboss.customer.feature.home.component.home.footer.FooterTrustSection
import com.policyboss.customer.feature.home.component.home.footer.TrustedPartnersSection.TrustedPartnersSection
import com.policyboss.customer.feature.home.component.home.header.HomeCollapsedTopBar
import com.policyboss.customer.feature.home.component.home.heroSection.HeroSection
import com.policyboss.customer.feature.home.component.home.vaultSection.PolicyVaultSection
import com.policyboss.customer.feature.home.component.home.videoSection.VideoSliderSection
import com.policyboss.customer.feature.home.model.homeState.HomeAction
import com.policyboss.customer.feature.home.model.homeState.HomeUiState
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.PolicyProtectedBottomSheet
import com.policyboss.customer.ui.components.collapsing.CollapsingScaffold
import com.policyboss.customer.ui.components.divider.SectionDivider
import com.policyboss.customer.ui.theme.AppColors


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




//******************************** Collapsing Logic************************************************************
/*
Imagine collapseFraction is a timeline from 0.0 (fully expanded) to 1.0 (fully collapsed).

The OLD broken math:

At 0.5 (halfway scrolled), the HeroSection reached 0f alpha (invisible).

At 0.5, the TopBar was still calculating as 0f alpha (invisible).

Result: From 0.5 to 0.7, both elements were invisible.

The NEW overlapping math:

expandedAlpha = (1f - (collapseFraction / 0.75f))

This means the HeroSection stays visible longer. It doesn't become completely invisible until collapseFraction hits 0.75 (75%).

collapsedAlpha = ((collapseFraction - 0.4f) / 0.6f)

This means the TopBar wakes up earlier. It starts fading in at 0.40 (40%).

Result: Between 40% and 75% of the scroll, the HeroSection is fading out at the exact same time the TopBar is fading in. This creates a perfect, TV-quality crossfade.


 */
//********************************************************************************************


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit
) {
    // =====================================================
    // HEADER HEIGHTS
    // These are RAW content heights. Your original CollapsingScaffold
    // adds the status-bar height internally safely.
    // =====================================================
    val heroContentHeight = 338.dp
    val collapsedTopBarHeight = 64.dp

    // 🚀 We are back to your exact, original signature parameters!
    CollapsingScaffold(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White),

        expandedHeaderHeight = heroContentHeight,
        collapsedHeaderHeight = collapsedTopBarHeight,

        // =================================================
        // OVERLAPPING CONTENT (Uses your exact parameter name)
        // =================================================
        collapsingContent = { collapseFraction, currentHeaderHeight ->

            val expandedAlpha = (1f - (collapseFraction / 0.75f)).coerceIn(0f, 1f)
            val collapsedAlpha = ((collapseFraction - 0.4f) / 0.6f).coerceIn(0f, 1f)
            val shadowElevation = if (collapseFraction > 0.95f) 8.dp else 0.dp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(currentHeaderHeight)
                    .clipToBounds() // Protects list from visual overflow
            ) {
                // LAYER B: COLLAPSED TOP BAR (Drawn Behind)
                if (collapsedAlpha > 0.01f) {
                    HomeCollapsedTopBar(
                        userName = uiState.userName,
                        initials = uiState.userInitials,
                        onProfileClick = { onAction(HomeAction.OnProfileClick) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                collapsedTopBarHeight + WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                            )
                            .shadow(shadowElevation)
                            .graphicsLayer { alpha = collapsedAlpha }
                    )
                }

                // LAYER A: EXPANDED HERO (Drawn On Top)
                if (expandedAlpha > 0.01f) {
                    HeroSection(
                        userName = uiState.userName,
                        initials = uiState.userInitials,
                        promoBanners = uiState.promoBanners,
                        onProfileClick = { onAction(HomeAction.OnProfileClick) },
                        onBannerClick = { banner -> onAction(HomeAction.OnPromoBannerClick(banner.id)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            // Binds to exact scaffold height
                            .height(currentHeaderHeight)
                            .offset(y = (-collapseFraction * 100).dp)
                            .graphicsLayer { alpha = expandedAlpha }
                    )
                }
            }
        },

        // =====================================================
        // BODY (Uses your exact parameter name)
        // =====================================================
        bodyContent = { dynamicPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = dynamicPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding() + 32.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // =================================================
                // QUICK ACTIONS
                // =================================================
                item {
                    QuickActionsGrid(
                        actions = uiState.quickActions,
                        onClick = { onAction(HomeAction.OnQuickActionClick(it)) },
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                        // 🚀 REMOVED ALL NEGATIVE OFFSETS
                        // The Scaffold math is now perfect. The cards will
                        // naturally sit exactly flush against the 56.dp white curve!
                    )
                }

                // =================================================
                // EARNING OPPORTUNITY
                // =================================================
                item {
                    EarningOpportunitySection(
                        banners = uiState.earningBanners,
                        onJoinPrivilegeClick = { onAction(HomeAction.OnPrivilegeBannerClick) },
                        onBannerClick = { bannerId -> onAction(HomeAction.OnEarningBannerClick(bannerId)) },
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                // =================================================
                // ASSISTANCE
                // =================================================
                item {
                    AssistanceSection(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        onClick = { onAction(HomeAction.OnAssistanceClick) }
                    )
                }

                // =================================================
                // DIVIDER
                // =================================================
                item {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        color = Color(0xFFEAECF0)
                    )
                }

                // =================================================
                // POLICY VAULT
                // =================================================
                item {
                    val selectedPolicies = uiState.vaultPolicies.filter { it.tabId == uiState.selectedVaultTab }

                    PolicyVaultSection(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        selectedTab = uiState.selectedVaultTab,
                        policies = selectedPolicies,
                        onTabSelected = { onAction(HomeAction.OnVaultTabSelected(it)) },
                        onViewAllClick = { onAction(HomeAction.OnViewAllVaultClick) },
                        onRenewClick = { policy -> onAction(HomeAction.OnShowPolicyBottomSheetClick(policy)) },
                        onViewDetailsClick = { /* pending Action */ }
                    )
                }

                // =================================================
                // SECTION DIVIDER
                // =================================================
                item {
                    SectionDivider()
                }

                // =================================================
                // CURATED POLICIES
                // =================================================
                item {
                    Text(
                        text = "Curated Policies - Just for you",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                item {
                    PolicyCategoryGrid(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        policies = uiState.curatedPolicies,
                        onPolicyClick = { onAction(HomeAction.OnPolicyCategoryClick(it)) }
                    )
                }

                // =================================================
                // BOSSPEDIA
                // =================================================
                item {
                    BosspediaSection(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        stories = uiState.bosspediaStories,
                        articles = uiState.bosspediaArticles,
                        onExploreMoreClick = { onAction(HomeAction.OnExploreBosspediaClick) },
                        onArticleClick = { /* Handle */ },
                        onStoryClick = { /* Handle */ }
                    )
                }

                // =================================================
                // VIDEOS
                // =================================================
                item {
                    if (uiState.videos.isNotEmpty()) {
                        VideoSliderSection(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            videos = uiState.videos,
                            onViewMoreClick = { onAction(HomeAction.OnVideoViewMoreClick) },
                            onVideoClick = { video -> onAction(HomeAction.OnVideoClick(video.id)) }
                        )
                    }
                }

                // =================================================
                // FOOTERS
                // =================================================
                item {
                    FooterTrustSection(modifier = Modifier.padding(horizontal = 24.dp))
                }

                item {
                    TrustedPartnersSection(modifier = Modifier.padding(horizontal = 24.dp))
                }
            }
        }
    )

    // =====================================================
    // POLICY BOTTOM SHEET
    // =====================================================
    uiState.selectedVaultPolicy?.let { policy ->
        PolicyProtectedBottomSheet(
            policy = policy,
            onDismiss = { onAction(HomeAction.OnDismissPolicyBottomSheet) }
        )
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
            },
            modifier = Modifier,
            contentPadding = PaddingValues(),
        )
    }
}



