package com.policyboss.customer.feature.home.ui.main.homeVault


// 1. Compose Core Imports


/*

The Mental Model: Two Independent Layers
Instead of trying to put the header inside the scrollable list, this pattern stacks two independent layers inside a root Box:

Layer 1 (The Bottom Layer): The LazyColumn containing your data.

Layer 2 (The Top Overlay): A pinned Column containing your Header and Tab Bar.

Because they are physically separated in the UI tree, the header can shrink and grow without forcing the LazyColumn to completely recompose or remeasure its children.

The Engine: NestedScrollConnection
The true magic happens in the nestedScroll modifier attached to the root Box. It acts as a "traffic cop" that intercepts the user's swipe gestures before the LazyColumn gets them.

It manages a single source of truth: headerOffsetPx (which goes from 0f when fully expanded, down to a negative maximum value when fully collapsed).

Swiping Up (onPreScroll): When the user pushes up, the traffic cop intercepts the scroll delta. Instead of letting the list scroll, it uses that energy to make headerOffsetPx more negative (shrinking the header). Only when the header is fully collapsed does it let the remaining swipe energy pass through to the LazyColumn.

Swiping Down (onPostScroll): When pulling down, the traffic cop lets the LazyColumn handle the scroll first. If the list is already at the very top and the user keeps pulling, the list says "I can't scroll anymore." The traffic cop takes that leftover energy and uses it to push headerOffsetPx back toward 0f, expanding the header.

The "Secret Sauce": Dynamic Content Padding
The biggest challenge with collapsing headers in Compose is keeping the top of the list perfectly flush against the bottom of the shrinking header.

Many developers try to use a Spacer inside the list, or they try to Modifier.offset the entire LazyColumn. This causes a jarring gap between the header and the list because the NestedScrollConnection is stealing the scroll events, meaning the list itself isn't technically scrolling yet, so the spacer doesn't move.

Your code solves this perfectly by dynamically animating the contentPadding.top of the LazyColumn.

Here is the logic:

You set the baseline top padding to the absolute maximum height of the header + tab bar.

You add the headerOffsetPx (converted to Dp) directly to that padding.

Because headerOffsetPx is a negative number when collapsing, it essentially subtracts padding frame-by-frame.

If the header shrinks by 10 pixels, the list's top padding shrinks by exactly 10 pixels at the exact same millisecond. The visual start of your list items slides upward perfectly in sync with the collapsing overlay, with zero gaps and zero overlap.

Why this is the "Best Way" (Architecture Benefits)
Deterministic Sizing: By hardcoding the Tab Bar height (52.dp) instead of relying on onGloballyPositioned to measure it at runtime, you eliminate layout passes. The UI knows exactly how big the bounding boxes are on the very first frame.

No List-State Dependency: Notice how you never read lazyListState.firstVisibleItemScrollOffset to calculate the header collapse? Reading list offsets causes massive recomposition overhead. Relying strictly on the NestedScrollConnection delta is highly performant.

Bottom Navigation Safe: Because the LazyColumn itself is never offset or moved (only its inner padding changes), the bottom of the list remains perfectly anchored to the bottom of the screen. You won't get empty gaps at the bottom of the viewport.

How to Implement This in Multiple Places
To reuse this across your application, you should extract the "Engine" into a wrapper component.

You would create a custom Composable (e.g., CollapsingToolbarScaffold) that accepts three slots:

collapsingHeaderContent: A Composable lambda for the shrinking part.

pinnedHeaderContent: A Composable lambda for the tabs/filters that stick to the top.

listContent: A Composable lambda that gives you the dynamically calculated PaddingValues to apply to whatever LazyColumn or LazyVerticalGrid you want to render underneath.

By isolating the NestedScrollConnection math and the padding calculations into a parent wrapper, your individual screens only have to worry about drawing the UI, not calculating pixels.
 */


//////////////////////////////////////////////


/*
 **************************************************************************
📱 Z-AXIS (LAYERS STACK)

(FRONT)
LAYER 2: PINNED OVERLAY (Column)
┌──────────────────────────────────────┐
│ 🟦 COLLAPSING HEADER                 │ ↕️ Height animates:
│    [<-] Policy Vault                 │    Expanded: 220dp
│    Manage and track...               │    Collapsed: 56dp
├──────────────────────────────────────┤
│ ⬜ VAULT TAB BAR                     │ ↕️ Height is FIXED:
│    [ Active ] [ Past ] [ All ]       │    Always 52dp
└──────────────────────────────────────┘
                   ↓
              OVERLAPPING
                   ↓
(BACK)
LAYER 1: SCROLLABLE LIST (LazyColumn)
┌──────────────────────────────────────┐
│ 🟨 DYNAMIC TOP PADDING               │ ↕️ Padding matches Layer 2 exactly.
│    (Pushes content down so it is     │    Shrinks dynamically as you scroll up.
│     not hidden behind the header)    │
├──────────────────────────────────────┤
│ 📄 Total Policies: 12                │ ↕️ Normal scrolling content.
│ 💳 [ Motor Policy Card ]             │    Moves up and disappears under
│ 💳 [ Motor Policy Card ]             │    Layer 2.
└──────────────────────────────────────┘
 **************************************************************************
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.component.home.vaultSection.component.MotorPolicyCard
import com.policyboss.customer.feature.home.component.home.vaultSection.component.VaultTabBar
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun HomeAllVaultScreen(
    contentPadding: PaddingValues,
    selectedTab: Int,
    policies: List<VaultPolicy>,
    onTabSelected: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRenewClick: (VaultPolicy) -> Unit,
    onViewDetailsClick: (VaultPolicy) -> Unit
) {

    // ============================================================
    // 1. LIST STATE
    // ============================================================

    val lazyListState = rememberLazyListState()

    val density = LocalDensity.current


    // ============================================================
    // 2. HEADER CONFIGURATION
    // ============================================================

    val expandedHeaderHeight = 220.dp

    val collapsedHeaderHeight = 56.dp

    val statusBarHeight =
        WindowInsets.statusBars
            .asPaddingValues()
            .calculateTopPadding()

    val actualExpandedHeaderHeight =
        expandedHeaderHeight + statusBarHeight

    val actualCollapsedHeaderHeight =
        collapsedHeaderHeight + statusBarHeight


    // ============================================================
    // 3. TAB BAR CONFIGURATION
    // ============================================================

    val VaultTabBarHeight = 52.dp


    // ============================================================
    // 4. COLLAPSE RANGE
    // ============================================================

    val maxHeaderHeightPx =
        with(density) {
            actualExpandedHeaderHeight.toPx()
        }

    val minHeaderHeightPx =
        with(density) {
            actualCollapsedHeaderHeight.toPx()
        }

    val collapseRangePx =
        (maxHeaderHeightPx - minHeaderHeightPx).coerceAtLeast(0f)


    // ============================================================
    // 5. HEADER OFFSET
    // ============================================================

    /*
     * 0f                    = fully expanded
     * -collapseRangePx      = fully collapsed
     */
    var headerOffsetPx by remember {
        mutableFloatStateOf(0f)
    }


    // ============================================================
    // 6. NESTED SCROLL CONNECTION
    // ============================================================

    val nestedScrollConnection =
        remember(collapseRangePx) {

            object : NestedScrollConnection {

                // SCROLL UP — header collapses first
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {

                    val deltaY = available.y

                    if (deltaY >= 0f) {
                        return Offset.Zero
                    }

                    val previousOffset = headerOffsetPx

                    headerOffsetPx =
                        (headerOffsetPx + deltaY).coerceIn(
                            -collapseRangePx,
                            0f
                        )

                    val consumedY = headerOffsetPx - previousOffset

                    return Offset(x = 0f, y = consumedY)
                }

                // SCROLL DOWN — header expands only after list is
                // already at its top and has leftover scroll
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {

                    val deltaY = available.y

                    if (deltaY <= 0f) {
                        return Offset.Zero
                    }

                    val previousOffset = headerOffsetPx

                    headerOffsetPx =
                        (headerOffsetPx + deltaY).coerceIn(
                            -collapseRangePx,
                            0f
                        )

                    val consumedY = headerOffsetPx - previousOffset

                    return Offset(x = 0f, y = consumedY)
                }
            }
        }


    // ============================================================
    // 7. CURRENT HEADER HEIGHT
    // ============================================================

    val headerHeightPx = maxHeaderHeightPx + headerOffsetPx

    val headerHeight =
        with(density) {
            headerHeightPx.toDp()
        }


    // ============================================================
    // 8. COLLAPSE FRACTION
    // ============================================================

    val collapseFraction by remember(headerOffsetPx, collapseRangePx) {

        derivedStateOf {

            if (collapseRangePx <= 0f) {
                0f
            } else {
                (-headerOffsetPx / collapseRangePx).coerceIn(0f, 1f)
            }
        }
    }


    // ============================================================
    // 9. HEADER ALPHA
    // ============================================================

    val expandedContentAlpha =
        (1f - collapseFraction * 1.6f).coerceIn(0f, 1f)

    val collapsedContentAlpha =
        ((collapseFraction - 0.7f) * 3.3f).coerceIn(0f, 1f)


    // ============================================================
    // 10. ROOT
    // ============================================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(nestedScrollConnection)
    ) {

        // ========================================================
        // LAYER 1 — LAZY COLUMN
        // ========================================================

        /*
         * ====================================================
         * ✅ THE FIX
         * ====================================================
         *
         * We no longer push content down with a Spacer item.
         * That approach only "shrinks" when the LazyColumn's
         * OWN scroll offset changes — but during the collapse
         * phase, onPreScroll intercepts the delta and the
         * LazyColumn never scrolls, so the overlay shrinks
         * while the spacer stays full height -> the gap you
         * saw in the screenshot.
         *
         * Instead:
         *   - contentPadding.top is FIXED at the fully-expanded
         *     header + tab bar height.
         *   - The whole LazyColumn is translated upward by
         *     `headerOffsetPx`, i.e. by exactly the same amount
         *     the header has shrunk by, on every single frame.
         *
         * Because both the overlay's bottom edge and the list's
         * visible top edge move by the identical delta at the
         * same time, they always stay perfectly flush — no gap,
         * no overlap, at any point during the drag, not just at
         * the two end states.
         *
         * Once the header is fully collapsed (headerOffsetPx ==
         * -collapseRangePx), the offset is pinned at its max and
         * further upward scroll is simply consumed by the
         * LazyColumn's own internal scrolling (untouched by us).
         * Scrolling back down re-expands the header exactly the
         * same way, via onPostScroll.
         */

        // headerOffsetPx (px) -> Dp, recomputed every frame the header moves.
// 0f   when fully expanded
// -collapseRangePx (in dp) when fully collapsed
        val headerOffsetDp = with(density) { headerOffsetPx.toDp() }

        LazyColumn(
            state = lazyListState,

            modifier = Modifier
                .fillMaxSize(), // ✅ no offset — viewport stays put, no bottom gap

            contentPadding = PaddingValues(
                // ✅ shrinks in real time as the header collapses, straight from
                // headerOffsetPx — NOT from lazyListState's own scroll amount.
                // That's what keeps it in sync during the pre-scroll phase where
                // the list itself hasn't scrolled yet.
                top = actualExpandedHeaderHeight + VaultTabBarHeight + headerOffsetDp,

                bottom = contentPadding.calculateBottomPadding() + 24.dp
            )
        ) {

            // ====================================================
            // TOTAL POLICIES
            // ====================================================

            item {

                Text(
                    text = "Total Policies: ${policies.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    )
                )
            }


            // ====================================================
            // EMPTY STATE
            // ====================================================

            if (policies.isEmpty()) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 64.dp),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "No policies found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

            } else {

                // =================================================
                // POLICY LIST
                // =================================================

                items(
                    items = policies,
                    key = { it.id }
                ) { policy ->

                    MotorPolicyCard(
                        policy = policy,

                        modifier = Modifier.padding(
                            horizontal = 24.dp,
                            vertical = 8.dp
                        ),

                        onRenewClick = { onRenewClick(policy) },

                        onViewDetailsClick = { onViewDetailsClick(policy) }
                    )
                }
            }
        }


        // ========================================================
        // LAYER 2 — HEADER + TAB BAR (pinned overlay)
        // ========================================================

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // ====================================================
            // COLLAPSING HEADER
            // ====================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .shadow(
                        elevation = if (collapseFraction > 0.9f) 8.dp else 0.dp
                    )
                    .background(MaterialTheme.colorScheme.primary)
            ) {

                // =================================================
                // EXPANDED CONTENT
                // =================================================

                if (expandedContentAlpha > 0.01f) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = collapsedHeaderHeight + statusBarHeight,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 16.dp
                            )
                            .alpha(expandedContentAlpha),

                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Policy Vault",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Manage and track all your insurance policies in one secure place.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }


                // =================================================
                // COLLAPSED TOOLBAR
                // =================================================

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(actualCollapsedHeaderHeight)
                        .padding(top = statusBarHeight)
                        .padding(horizontal = 16.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(42.dp)
                    ) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Policy Vault",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .weight(1f)
                            .alpha(collapsedContentAlpha)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Spacer(modifier = Modifier.size(42.dp))
                }
            }


            // ====================================================
            // PINNED TAB BAR
            // ====================================================

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(VaultTabBarHeight),

                color = MaterialTheme.colorScheme.background,

                shadowElevation = if (collapseFraction > 0f) 4.dp else 0.dp
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    VaultTabBar(
                        tabs = AppDummyData.vaultTabs,
                        selectedTab = selectedTab,
                        onTabSelected = onTabSelected
                    )
                }
            }
        }
    }
}


// =================================================================
// PREVIEW
// =================================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Home All Vault - Populated"
)
@Composable
private fun HomeAllVaultScreenPreview() {

    PolicyBossCustomerTheme {

        HomeAllVaultScreen(
            contentPadding = PaddingValues(0.dp),
            selectedTab = 0,
            policies = AppDummyData.vaultPolicies,
            onTabSelected = {},
            onBackClick = {},
            onRenewClick = {},
            onViewDetailsClick = {}
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Home All Vault - Empty"
)
@Composable
private fun HomeAllVaultScreenEmptyPreview() {

    PolicyBossCustomerTheme {

        HomeAllVaultScreen(
            contentPadding = PaddingValues(0.dp),
            selectedTab = 2,
            policies = emptyList(),
            onTabSelected = {},
            onBackClick = {},
            onRenewClick = {},
            onViewDetailsClick = {}
        )
    }
}