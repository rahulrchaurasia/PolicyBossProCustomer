package com.policyboss.customer.feature.mainScreen.customBottomNavBar


import androidx.compose.material3.*

import androidx.compose.foundation.layout.size

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors


import androidx.compose.material3.LocalContentColor
import com.policyboss.customer.feature.mainScreen.BottomNavItem

/**
 * =========================================================
 * BOTTOM NAVIGATION BAR
 * =========================================================
 */

@Composable
fun CustomBottomNavigationBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    NavigationBar(

        containerColor = Color.White,

        tonalElevation = 10.dp
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(

                selected = selectedIndex == index,

                onClick = {

                    onItemSelected(index)
                },

                icon = {

                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,

                        modifier = Modifier.size(item.iconSize),

                        tint = if (item.preserveOriginalColor) {
                            Color.Unspecified
                        } else {
                            LocalContentColor.current
                        }
                    )
                },

                label = {

                    Text(
                        text = item.title,

                        style = MaterialTheme.typography.labelSmall,

                        color = if (selectedIndex == index) {
                            AppColors.PrimaryBlue
                        } else {
                            AppColors.TextSecondary
                        }
                    )
                },

                colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = AppColors.PrimaryBlue,

                    selectedTextColor = AppColors.PrimaryBlue,

                    unselectedIconColor = AppColors.TextSecondary,

                    unselectedTextColor = AppColors.TextSecondary,

                    indicatorColor = AppColors.PrimaryBlue.copy(alpha = 0.10f)
                )
            )
        }
    }
}
