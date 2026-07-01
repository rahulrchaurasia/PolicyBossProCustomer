package com.policyboss.customer.feature.mainScreen.customBottomNavBar


import androidx.compose.material3.*

import androidx.compose.foundation.layout.size




import androidx.compose.material3.LocalContentColor
import com.policyboss.customer.feature.mainScreen.BottomNavItem




import androidx.compose.animation.animateContentSize

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




import com.policyboss.customer.ui.theme.AppColors

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
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






import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.*
import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.ui.theme.bottomNavLabelSelected

@Composable
fun CustomBottomNavigationBar(

    items: List<BottomNavItem>,

    currentDestination: NavDestination?,

    onTabSelected: (Dest) -> Unit

) {

    /**
     * =====================================================
     * DETERMINE SELECTED TAB
     * =====================================================
     */
    val selectedIndex = items.indexOfFirst { item ->

        when (item.destination) {

            Dest.Home ->
                currentDestination?.hasRoute<Dest.Home>() == true

            Dest.ClaimSupport ->
                currentDestination?.hasRoute<Dest.ClaimSupport>() == true

            Dest.PolicyVault ->
                currentDestination?.hasRoute<Dest.PolicyVault>() == true

            Dest.Privilege ->
                currentDestination?.hasRoute<Dest.Privilege>() == true

            else -> false
        }

    }.coerceAtLeast(0)

    val safeSelectedIndex = selectedIndex.coerceIn(
        0,
        items.lastIndex
    )

    /**
     * =====================================================
     * OUTER CONTAINER
     * =====================================================
     */
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.Background)
            .windowInsetsPadding(WindowInsets.navigationBars)

    ) {

        /**
         * =====================================================
         * FLOATING BAR
         * =====================================================
         */
        Surface(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ),

            color = AppColors.Surface,

            shape = RoundedCornerShape(24.dp),

            border = BorderStroke(
                1.dp,
                AppColors.BorderSecondary
            ),

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

                /**
                 * =====================================================
                 * ANIMATED INDICATOR
                 * =====================================================
                 */
                val indicatorOffset by animateDpAsState(

                    targetValue =

                        (tabWidth * safeSelectedIndex) +
                                (tabWidth - indicatorWidth) / 2,

                    animationSpec = tween(

                        durationMillis = 300,

                        easing = FastOutSlowInEasing

                    ),

                    label = "BottomIndicator"

                )

                /**
                 * =====================================================
                 * SLIDING PILL
                 * =====================================================
                 */
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

                // ---------- Part 2B starts here ----------

                /**
                 * =====================================================
                 * NAVIGATION ITEMS
                 * =====================================================
                 */
                //region NAVIGATION ITEMS
                Row(

                    modifier = Modifier.fillMaxSize()

                ) {

                    items.forEachIndexed { index, item ->

                        val isSelected = safeSelectedIndex == index

                        Column(

                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .selectable(

                                    selected = isSelected,

                                    onClick = {

                                        if (!isSelected) {

                                            onTabSelected(item.destination)

                                        }

                                    },

                                    role = Role.Tab,

                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },

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

                                    item.preserveOriginalColor ->
                                        Color.Unspecified

                                    isSelected ->
                                        AppColors.PrimaryBlue

                                    else ->
                                        AppColors.TextSecondary

                                }

                            )

                            Spacer(

                                modifier = Modifier.height(4.dp)

                            )

                            Text(

                                text = item.title,


                              //  style =  MaterialTheme.typography.labelSmall,

                                style = if (isSelected) {
                                    MaterialTheme.typography.bottomNavLabelSelected
                                } else {
                                    MaterialTheme.typography.labelSmall
                                },


                                color = if (isSelected)
                                    AppColors.PrimaryBlue
                                else
                                    AppColors.TextSecondary

                            )

                        }

                    }

                }

                //endregion

            }

        }

    }

}





