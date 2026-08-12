package com.policyboss.customer.ui.components.collapsing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
📝 Golden Rules for CollapsingScaffold
Always Consume the dynamicPadding:
The scaffold calculates the exact padding needed to prevent the list from hiding behind the header. You must pass the padding provided by bodyContent directly into your LazyColumn's contentPadding. If you forget this, your list will clip under the header.

Use graphicsLayer for Animations:
Whenever you use the collapseFraction to fade (alpha), shrink (scale), or move (translationY) elements inside the header, wrap them in a Modifier.graphicsLayer { ... }. This offloads the math to the GPU and prevents lag.

Empty Pinned Content:
If a screen does not need a sticky tab bar, omit the pinnedContentHeight (it defaults to 0.dp) and omit the pinnedContent block. The scaffold will automatically act as a standard collapsing header.

Previews are Static by Default:
A standard @Preview will render the scaffold in its fully expanded state because no scroll events have occurred yet. To test the collapsing behavior without launching the emulator, use Android Studio's Interactive Mode (the small finger icon above the preview window).
 */

/**
 * A highly reusable scaffold that handles the complex math of nested scrolling,
 * collapsing headers, and pinned (sticky) sub-headers.
 */
@Composable
fun CollapsingScaffold(
    expandedHeaderHeight: Dp,
    collapsedHeaderHeight: Dp,
    pinnedContentHeight: Dp = 0.dp, // Defaults to 0 if you don't have a tab bar
    bottomPadding: Dp = 24.dp, // Padding for the bottom of the list
    modifier: Modifier = Modifier,
    // We pass the collapseFraction (0f = expanded, 1f = collapsed) so the UI can animate alpha/scale
    collapsingContent: @Composable ColumnScope.(collapseFraction: Float, headerHeight: Dp) -> Unit,
    pinnedContent: @Composable ColumnScope.() -> Unit = {},
    bodyContent: @Composable (contentPadding: PaddingValues) -> Unit
) {
    val density = LocalDensity.current

    // 1. Calculate Status Bar Insets dynamically
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val actualExpandedHeight = expandedHeaderHeight + statusBarHeight
    val actualCollapsedHeight = collapsedHeaderHeight + statusBarHeight

    // 2. Pixel Math
    val maxHeaderHeightPx = with(density) { actualExpandedHeight.toPx() }
    val minHeaderHeightPx = with(density) { actualCollapsedHeight.toPx() }
    val collapseRangePx = (maxHeaderHeightPx - minHeaderHeightPx).coerceAtLeast(0f)

    var headerOffsetPx by remember { mutableFloatStateOf(0f) }

    // 3. The Scroll Engine (Traffic Cop)
    val nestedScrollConnection = remember(collapseRangePx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val deltaY = available.y
                if (deltaY >= 0f) return Offset.Zero // Only handle scroll UP here

                val previousOffset = headerOffsetPx
                headerOffsetPx = (headerOffsetPx + deltaY).coerceIn(-collapseRangePx, 0f)
                return Offset(x = 0f, y = headerOffsetPx - previousOffset)
            }

            override fun onPostScroll(
                consumed: Offset, available: Offset, source: NestedScrollSource
            ): Offset {
                val deltaY = available.y
                if (deltaY <= 0f) return Offset.Zero // Only handle scroll DOWN leftover here

                val previousOffset = headerOffsetPx
                headerOffsetPx = (headerOffsetPx + deltaY).coerceIn(-collapseRangePx, 0f)
                return Offset(x = 0f, y = headerOffsetPx - previousOffset)
            }
        }
    }

    // 4. Derived States for UI
    val headerHeightPx = maxHeaderHeightPx + headerOffsetPx
    val currentHeaderHeight = with(density) { headerHeightPx.toDp() }
    
    val collapseFraction by remember(headerOffsetPx, collapseRangePx) {
        derivedStateOf {
            if (collapseRangePx <= 0f) 0f else (-headerOffsetPx / collapseRangePx).coerceIn(0f, 1f)
        }
    }

    val headerOffsetDp = with(density) { headerOffsetPx.toDp() }

    // 5. Build the Layout
    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        // LAYER 1: The List (Provides dynamic padding back to the screen)
        bodyContent(
            PaddingValues(
                top = actualExpandedHeight + pinnedContentHeight + headerOffsetDp,
                bottom = bottomPadding
            )
        )

        // LAYER 2: The Overlay (Collapsing Header + Sticky Content)
        Column(modifier = Modifier.fillMaxWidth()) {
            collapsingContent(collapseFraction, currentHeaderHeight)
            pinnedContent()
        }
    }
}