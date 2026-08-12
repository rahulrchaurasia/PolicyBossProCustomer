package com.policyboss.customer.feature.mainScreen.customBottomNavBar


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import androidx.navigation.NavDestination.Companion.hierarchy
import com.policyboss.customer.navigation.isInGraph// 🚀 FIX: This fixes the KClass vs String error!
import com.policyboss.customer.ui.theme.AppColors



import androidx.navigation.NavDestination
import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.navigation.isInGraph
import com.policyboss.customer.ui.theme.bottomNavLabelSelected
import com.policyboss.customer.ui.theme.captionSmall
import kotlin.reflect.KClass

import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import com.policyboss.customer.ui.theme.bottomNavLabelUnSelected


/**
 * 🚀 FIX: isInGraph Extension Required to handle this
 *
 */
data class BottomNavItem(
    val icon: Int,
    val title: String,
    val graphDestination: Dest,
    val rootScreen: KClass<out Dest>,
    val preserveOriginalColor: Boolean = false,
    val iconSize: Dp = 22.dp
)

@Composable
fun CustomBottomNavigationBar(
    items: List<BottomNavItem>,
    currentDestination: NavDestination?,
    onTabSelected: (Dest) -> Unit
) {

//    val selectedIndex = items.indexOfFirst { item ->
//        currentDestination.isSelectedGraph(item.graphDestination)
//    }.coerceAtLeast(0)

    val selectedIndex = items.indexOfFirst {
        currentDestination.isSelectedGraph(it.graphDestination)
    }

    val safeSelectedIndex =
        selectedIndex.coerceIn(0, items.lastIndex)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            // Fallback to Color.White if AppColors is not imported
            .background(AppColors.Background) // Restored
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            color = AppColors.Surface,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, AppColors.BorderSecondary),
            shadowElevation = 8.dp,
            tonalElevation = 0.dp
        ) {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {

                val tabWidth = maxWidth / items.size
                val indicatorWidth = 60.dp

               // val targetOffset = tabWidth * selectedIndex + (tabWidth - indicatorWidth) / 2

                val targetOffset =
                    tabWidth * safeSelectedIndex +
                            (tabWidth - indicatorWidth) / 2

//                val indicatorOffset by animateDpAsState(
//                    targetValue = targetOffset,
//                    animationSpec = tween(
//                        durationMillis = 150,
//                        easing = FastOutSlowInEasing
//                    ),
//                    label = "BottomIndicator"
//                )

                // ==========================================
                // 🚀 CHANGE 1: ANIMATION UPDATE
                // ==========================================
                val indicatorOffset by animateDpAsState(
                    targetValue = targetOffset,
                    // Replaced tween() with spring() right here:
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy, // No bounce, just snappy
                        stiffness = Spring.StiffnessMediumLow       // Controls the speed
                    ),
                    label = "BottomIndicator"
                )

                Box(
                    modifier = Modifier
                        .offset(x = indicatorOffset)
                        .padding(top = 10.dp)
                        .width(indicatorWidth)
                        .height(34.dp)
                        .background(
                            color = AppColors.PrimaryBlue.copy(alpha = 0.10f),
                            shape = RoundedCornerShape(17.dp)
                        )
                )

                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items.forEachIndexed { index, item ->

                       // val isSelected = index == selectedIndex

                        val isSelected =
                            safeSelectedIndex == index

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .selectable(
                                    selected = isSelected,
                                    onClick = {
                                        if (!isSelected) {
                                            onTabSelected(item.graphDestination)
                                        }
                                    },
                                    role = Role.Tab,
                                    interactionSource = remember<MutableInteractionSource> { MutableInteractionSource() },
                                    indication = null
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(item.iconSize),
                                tint = when {
                                    item.preserveOriginalColor -> Color.Unspecified
                                    isSelected -> AppColors.PrimaryBlue
                                    else -> AppColors.TextSecondary
                                }
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // ==========================================
                            // 🚀 CHANGE 2: TITLE SIZE
                            // ==========================================
                            Text(
                                text = item.title,
                                style = if (isSelected) {
                                    MaterialTheme.typography.bottomNavLabelSelected // Restored
                                } else {
                                    MaterialTheme.typography.bottomNavLabelUnSelected
                                },
                                color = if (isSelected) {
                                    AppColors.PrimaryBlue // Restored
                                } else {
                                    AppColors.TextSecondary // Restored
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Checks if the current destination belongs to the provided Graph.
 */
/**
 * Determines whether this Bottom Navigation item should appear selected.
 *
 * This checks the entire navigation hierarchy.
 *
 * Example:
 *
 * Profile
 *   ↑
 * HomeGraph
 *   ↑
 * MainGraph
 *
 * Since Profile belongs to HomeGraph,
 * Explore tab remains selected.
 */
private fun NavDestination?.isSelectedGraph(
    graphDestination: Dest
): Boolean {
    return isInGraph(graphDestination)
}
/**
 * Returns true if the current destination belongs to the supplied destination
 * or one of its nested navigation graphs.
 */
/**
 * Returns true if the current destination belongs to the supplied destination
 * or one of its nested navigation graphs.
 */



//
//@Composable
//fun CustomBottomNavigationBar(
//
//    items: List<BottomNavItem>,
//
//    currentDestination: NavDestination?,
//
//    onTabSelected: (Dest) -> Unit
//
//) {
//
//    /**
//     * =====================================================
//     * DETERMINE SELECTED TAB
//     * =====================================================
//     */
//    val selectedIndex = items.indexOfFirst { item ->
//
//        when (item.destination) {
//
//            Dest.Home ->
//                currentDestination?.hasRoute<Dest.Home>() == true
//
//            Dest.ClaimSupport ->
//                currentDestination?.hasRoute<Dest.ClaimSupport>() == true
//
//            Dest.PolicyVault ->
//                currentDestination?.hasRoute<Dest.PolicyVault>() == true
//
//            Dest.Privilege ->
//                currentDestination?.hasRoute<Dest.Privilege>() == true
//
//            else -> false
//        }
//
//    }.coerceAtLeast(0)
//
//    val safeSelectedIndex = selectedIndex.coerceIn(
//        0,
//        items.lastIndex
//    )
//
//    /**
//     * =====================================================
//     * OUTER CONTAINER
//     * =====================================================
//     */
//    Box(
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(AppColors.Background)
//            .windowInsetsPadding(WindowInsets.navigationBars)
//
//    ) {
//
//        /**
//         * =====================================================
//         * FLOATING BAR
//         * =====================================================
//         */
//        Surface(
//
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    horizontal = 16.dp,
//                    vertical = 4.dp
//                ),
//
//            color = AppColors.Surface,
//
//            shape = RoundedCornerShape(24.dp),
//
//            border = BorderStroke(
//                1.dp,
//                AppColors.BorderSecondary
//            ),
//
//            shadowElevation = 8.dp,
//
//            tonalElevation = 0.dp
//
//        ) {
//
//            BoxWithConstraints(
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(80.dp)
//
//            ) {
//
//                val tabWidth = maxWidth / items.size
//
//                val indicatorWidth = 60.dp
//
//                /**
//                 * =====================================================
//                 * ANIMATED INDICATOR
//                 * =====================================================
//                 */
//                val indicatorOffset by animateDpAsState(
//
//                    targetValue =
//
//                        (tabWidth * safeSelectedIndex) +
//                                (tabWidth - indicatorWidth) / 2,
//
//                    animationSpec = tween(
//
//                        durationMillis = 300,
//
//                        easing = FastOutSlowInEasing
//
//                    ),
//
//                    label = "BottomIndicator"
//
//                )
//
//                /**
//                 * =====================================================
//                 * SLIDING PILL
//                 * =====================================================
//                 */
//                Box(
//
//                    modifier = Modifier
//                        .offset(x = indicatorOffset)
//                        .padding(top = 10.dp)
//                        .width(indicatorWidth)
//                        .height(34.dp)
//                        .background(
//
//                            color = AppColors.PrimaryBlue.copy(alpha = 0.10f),
//
//                            shape = RoundedCornerShape(17.dp)
//
//                        )
//
//                )
//
//                // ---------- Part 2B starts here ----------
//
//                /**
//                 * =====================================================
//                 * NAVIGATION ITEMS
//                 * =====================================================
//                 */
//                //region NAVIGATION ITEMS
//                Row(
//
//                    modifier = Modifier.fillMaxSize()
//
//                ) {
//
//                    items.forEachIndexed { index, item ->
//
//                        val isSelected = safeSelectedIndex == index
//
//                        Column(
//
//                            modifier = Modifier
//                                .weight(1f)
//                                .fillMaxHeight()
//                                .selectable(
//
//                                    selected = isSelected,
//
//                                    onClick = {
//
//                                        if (!isSelected) {
//
//                                            onTabSelected(item.destination)
//
//                                        }
//
//                                    },
//
//                                    role = Role.Tab,
//
//                                    interactionSource = remember {
//                                        MutableInteractionSource()
//                                    },
//
//                                    indication = null
//
//                                ),
//
//                            horizontalAlignment = Alignment.CenterHorizontally,
//
//                            verticalArrangement = Arrangement.Center
//
//                        ) {
//
//                            Icon(
//
//                                painter = painterResource(item.icon),
//
//                                contentDescription = item.title,
//
//                                modifier = Modifier.size(item.iconSize),
//
//                                tint = when {
//
//                                    item.preserveOriginalColor ->
//                                        Color.Unspecified
//
//                                    isSelected ->
//                                        AppColors.PrimaryBlue
//
//                                    else ->
//                                        AppColors.TextSecondary
//
//                                }
//
//                            )
//
//                            Spacer(
//
//                                modifier = Modifier.height(4.dp)
//
//                            )
//
//                            Text(
//
//                                text = item.title,
//
//
//                              //  style =  MaterialTheme.typography.labelSmall,
//
//                                style = if (isSelected) {
//                                    MaterialTheme.typography.bottomNavLabelSelected
//                                } else {
//                                    MaterialTheme.typography.labelSmall
//                                },
//
//
//                                color = if (isSelected)
//                                    AppColors.PrimaryBlue
//                                else
//                                    AppColors.TextSecondary
//
//                            )
//
//                        }
//
//                    }
//
//                }
//
//                //endregion
//
//            }
//
//        }
//
//    }
//
//}





