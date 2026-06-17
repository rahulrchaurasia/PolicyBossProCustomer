package com.policyboss.customer.feature.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.HomeState.HomeAction
import com.policyboss.customer.feature.home.model.HomeState.HomeUiState


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*

import androidx.compose.ui.graphics.Color
import com.policyboss.customer.feature.home.component.home.PolicyCategoryItem
import com.policyboss.customer.feature.home.component.home.vaultSection.PolicyVaultSection
import com.policyboss.customer.feature.home.component.home.footer.BosspediaSection
import com.policyboss.customer.feature.home.component.home.footer.FooterTrustSection


import androidx.compose.ui.tooling.preview.Preview

import com.policyboss.customer.feature.home.component.home.card.QuickActionCard
import com.policyboss.customer.feature.home.component.home.earningOpportunitySection.EarningOpportunitySection
import com.policyboss.customer.ui.components.divider.SectionDivider

import com.policyboss.customer.feature.home.component.home.heroSection.HeroSection
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.PolicyProtectedBottomSheet



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

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit
) {

    // Wrap the entire screen in a Box so you can layer UI elements

    Box(modifier = Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
//        contentPadding = PaddingValues(
//            bottom = 32.dp
//        ),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding() + 32.dp,
//            start = 16.dp, // Assuming you want side padding on the grid
//            end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {

            // =====================================================
            // 1. HERO / HEADER SECTION (Full Width)
            // =====================================================
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

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
            }

            // =====================================================
            // 2. QUICK ACTIONS GRID (Half Width)
            // =====================================================
            itemsIndexed(
                items = uiState.quickActions,
                key = { _, item -> item.id }
            ) { index, action ->

                // Pulls the first two cards up to overlap the Hero section slightly
                val topOffset = if (index < 2) (-42).dp else 0.dp

                // Adds specific padding based on left vs right column
                val cardModifier = if (index % 2 == 0) {
                    Modifier
                        .offset(y = topOffset)
                        .padding(start = 24.dp, end = 8.dp) // Left column
                } else {
                    Modifier
                        .offset(y = topOffset)
                        .padding(start = 8.dp, end = 24.dp) // Right column
                }

                QuickActionCard(
                    modifier = cardModifier,
                    title = action.title,
                    subtitle = action.subtitle,
                    imageRes = action.imageRes,
                    isPro = action.isPro,
                    isNew = action.isNew,
                    onClick = { onAction(HomeAction.OnQuickActionClick(action.id)) }
                )
            }


            // =====================================================
            // EARNING SECTION
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                SectionDivider()
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

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

            // =====================================================
            // POLICIES HEADER
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                SectionDivider()
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                Text(
                    text = "Curated Policies - Just for you",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    )
                )
            }

            // =====================================================
            // POLICY GRID
            // =====================================================

            itemsIndexed(
                items = uiState.curatedPolicies
            ) { index, policy ->

                val itemModifier =
                    if (index % 2 == 0) {

                        Modifier.padding(
                            start = 24.dp,
                            end = 8.dp
                        )

                    } else {

                        Modifier.padding(
                            start = 8.dp,
                            end = 24.dp
                        )
                    }

                PolicyCategoryItem(
                    title = policy,
                    modifier = itemModifier,
                    onClick = {
                        onAction(
                            HomeAction.OnPolicyCategoryClick(
                                policy
                            )
                        )
                    }
                )
            }

            // =====================================================
            // DIVIDER
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                HorizontalDivider(
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    ),
                    color = Color(0xFFEAECF0)
                )
            }

            // =====================================================
            // VAULT
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                PolicyVaultSection(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    policy = policy,
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected,
                    onViewAllClick = {
                        onAction(HomeAction.OnViewAllVaultClick)
                    },
                    onRenewClick = {
                        onAction(HomeAction.OnRenewClick(policy))
                    },
                    onViewDetailsClick = {
                        onAction(HomeAction.OnViewDetailsClick(policy))
                    }
                )
            }

            // =====================================================
            // BOSSPEDIA
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                BosspediaSection(
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    ),
                    onExploreMoreClick = {
                        onAction(
                            HomeAction.OnExploreBosspediaClick
                        )
                    }
                )
            }

            // =====================================================
            // FOOTER
            // =====================================================

            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {

                FooterTrustSection(
                    modifier = Modifier.padding(
                        horizontal = 24.dp
                    )
                )
            }

        }

        // =====================================================
        // 2. OVERLAYS / BOTTOM SHEETS (Drawn last, on top of the grid)
        // =====================================================
       //if selectedVaultPolicy  exists → sheet should open
        uiState.selectedVaultPolicy?.let { policy ->
            PolicyProtectedBottomSheet(
                policy = policy,
                onDismiss = {
                    onAction(HomeAction.OnDismissPolicyBottomSheet)
                }
            )
        }

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
        curatedPolicies = listOf("Health Insurance", "Life Insurance", "Motor Insurance", "Travel Insurance"),
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



