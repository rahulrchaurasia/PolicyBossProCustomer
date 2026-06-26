package com.policyboss.customer.feature.mainScreen.customBottomNavBar


import androidx.compose.material3.*

import androidx.compose.foundation.layout.size




import androidx.compose.material3.LocalContentColor
import com.policyboss.customer.feature.mainScreen.BottomNavItem




import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


// UI Layout and Components

import androidx.compose.ui.unit.dp

// Animations
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween


import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp


/**
 * =========================================================
 * BOTTOM NAVIGATION BAR
 * =========================================================
 */



//import androidx.compose.animation.core.FastOutSlowInEasing
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.selection.selectable
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.semantics.Role
//import androidx.compose.ui.unit.dp

import com.policyboss.customer.ui.theme.AppColors

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


import androidx.compose.ui.semantics.Role
/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1> Without this:

Modifier.navigationBarsPadding()

the bottom bar may overlap the gesture area on modern Android devices.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */




/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
2>✅ Do:

Surface {
    Box(
        modifier = Modifier.navigationBarsPadding()
    ) {
        // Bottom Bar
    }
}

❌ Avoid:

Surface(
modifier = Modifier.navigationBarsPadding()
)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */



/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3>✅ Do:

Scaffold(
bottomBar = {
    CustomBottomNavigationBar(...)
}
)

The bar is only used in one place.

Nothing outside should control:

    width
    padding
    alignment
    size

So a modifier isn't strictly required.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

 */


@Composable
fun CustomBottomNavigationBar(
   // modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val safeSelectedIndex = selectedIndex.coerceIn(0, items.lastIndex)

    // 1. OUTER CONTAINER: Handles background color and system inset padding
    // The background color here prevents the transparency issue.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.Background) // 1. Paint the color FIRST
            //.navigationBarsPadding() // Safely pushes the whole bar above gesture area


            .windowInsetsPadding(WindowInsets.navigationBars) // 2. Then apply the padding

    ) {

        // 2. FLOATING SURFACE: The actual pill-shaped bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            color =    AppColors.Surface,
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

                // Animate the pill position
                val indicatorOffset by animateDpAsState(
                    targetValue = (tabWidth * safeSelectedIndex) + (tabWidth - indicatorWidth) / 2,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = "BottomIndicator"
                )

                // The Sliding Pill
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

                // Navigation Items
                Row(modifier = Modifier.fillMaxSize()) {
                    items.forEachIndexed { index, item ->
                        val isSelected = safeSelectedIndex == index

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .selectable(
                                    selected = isSelected,
                                    onClick = { if (!isSelected) onItemSelected(index) },
                                    role = Role.Tab,
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null // Removes default ripple
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
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) AppColors.PrimaryBlue else AppColors.TextSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

//
//@Composable
//fun CustomBottomNavigationBar(
//    items: List<BottomNavItem>,
//    selectedIndex: Int,
//    onItemSelected: (Int) -> Unit
//) {
//
//    val safeSelectedIndex = selectedIndex.coerceIn(0, items.lastIndex)
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(
//                horizontal = 16.dp,
//                vertical = 12.dp
//            )
//            .navigationBarsPadding(),
//
//        color = AppColors.Surface,
//
//        shape = RoundedCornerShape(24.dp),
//
//        border = BorderStroke(
//            width = 1.dp,
//            color = AppColors.BorderSecondary
//        ),
//
//        shadowElevation = 8.dp,
//
//        tonalElevation = 0.dp
//    ) {
//
//        BoxWithConstraints(
//
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(80.dp)
//
//        ) {
//
//            val tabWidth = maxWidth / items.size
//            val indicatorWidth = 60.dp
//
//            val indicatorOffset by animateDpAsState(
//
//                targetValue =
//                    (tabWidth * safeSelectedIndex) +
//                            (tabWidth - indicatorWidth) / 2,
//
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing
//                ),
//
//                label = "BottomIndicator"
//
//            )
//
//            /**
//             * Selected Pill
//             */
//            Box(
//
//                modifier = Modifier
//                    .offset(x = indicatorOffset)
//                    .padding(top = 10.dp)
//                    .width(indicatorWidth)
//                    .height(34.dp)
//                    .background(
//                        color = AppColors.PrimaryBlue.copy(alpha = 0.10f),
//                        shape = RoundedCornerShape(17.dp)
//                    )
//
//            )
//
//            /**
//             * Navigation Items
//             */
//            Row(
//
//                modifier = Modifier.fillMaxSize()
//
//            ) {
//
//                items.forEachIndexed { index, item ->
//
//                    val isSelected = safeSelectedIndex == index
//
//                    Column(
//
//                        modifier = Modifier
//                            .weight(1f)
//                            .fillMaxHeight()
//                            .selectable(
//
//                                selected = isSelected,
//
//                                onClick = {
//
//                                    if (!isSelected) {
//                                        onItemSelected(index)
//                                    }
//                                },
//
//                                role = Role.Tab,
//
//                                interactionSource = remember {
//                                    MutableInteractionSource()
//                                },
//
//                                indication = null
//                            ),
//
//                        horizontalAlignment = Alignment.CenterHorizontally,
//
//                        verticalArrangement = Arrangement.Center
//
//                    ) {
//
//                        Icon(
//
//                            painter = painterResource(item.icon),
//
//                            contentDescription = item.title,
//
//                            modifier = Modifier.size(item.iconSize),
//
//                            tint = when {
//
//                                item.preserveOriginalColor ->
//                                    Color.Unspecified
//
//                                isSelected ->
//                                    AppColors.PrimaryBlue
//
//                                else ->
//                                    AppColors.TextSecondary
//                            }
//                        )
//
//                        Spacer(
//                            modifier = Modifier.height(4.dp)
//                        )
//
//                        Text(
//
//                            text = item.title,
//
//                            style = MaterialTheme.typography.labelSmall,
//
//                            color = if (isSelected)
//                                AppColors.PrimaryBlue
//                            else
//                                AppColors.TextSecondary
//                        )
//                    }
//                }
//            }
//        }
//    }
//}


//@Composable
//fun CustomBottomNavigationBar(
//    items: List<BottomNavItem>,
//    selectedIndex: Int,
//    onItemSelected: (Int) -> Unit
//) {
//    Surface(
//        color = Color.White,
//        tonalElevation = 10.dp,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//
//        Box(
//            modifier = Modifier.navigationBarsPadding()
//        ) {
//
//            BoxWithConstraints(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(80.dp)
//            ) {
//
//                val safeSelectedIndex =
//                    selectedIndex.coerceIn(0, items.lastIndex)
//
//                val tabWidth = maxWidth / items.size
//
//                val indicatorOffset by animateDpAsState(
//                    targetValue = tabWidth * safeSelectedIndex,
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    ),
//                    label = "indicatorOffset"
//                )
//
//                // Sliding Pill
//                Box(
//                    modifier = Modifier
//                        .offset(x = indicatorOffset)
//                        .width(tabWidth)
//                        .fillMaxHeight(),
//                    contentAlignment = Alignment.TopCenter
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .padding(top = 12.dp)
//                            .width(64.dp)
//                            .height(32.dp)
//                            .background(
//                                color = AppColors.PrimaryBlue.copy(alpha = 0.10f),
//                                shape = RoundedCornerShape(16.dp)
//                            )
//                    )
//                }
//
//                // Nav Items
//                Row(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//
//                    items.forEachIndexed { index, item ->
//
//                        val isSelected =
//                            safeSelectedIndex == index
//
//                        Column(
//                            modifier = Modifier
//                                .weight(1f)
//                                .fillMaxHeight()
//                                .clickable(
//                                    interactionSource = remember {
//                                        MutableInteractionSource()
//                                    },
//                                    indication = null
//                                ) {
//                                    onItemSelected(index)
//                                },
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//
//                            Icon(
//                                painter = painterResource(item.icon),
//                                contentDescription = item.title,
//                                modifier = Modifier
//                                    .size(item.iconSize ?: 24.dp)
//                                    .padding(bottom = 4.dp),
//                                tint = when {
//                                    item.preserveOriginalColor ->
//                                        Color.Unspecified
//
//                                    isSelected ->
//                                        AppColors.PrimaryBlue
//
//                                    else ->
//                                        AppColors.TextSecondary
//                                }
//                            )
//
//                            Text(
//                                text = item.title,
//                                style = MaterialTheme.typography.labelSmall,
//                                color = if (isSelected) {
//                                    AppColors.PrimaryBlue
//                                } else {
//                                    AppColors.TextSecondary
//                                }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

