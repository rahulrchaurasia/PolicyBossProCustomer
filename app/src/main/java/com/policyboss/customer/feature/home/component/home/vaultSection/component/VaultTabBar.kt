package com.policyboss.customer.feature.home.component.home.vaultSection.component

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.vault.VaultTabItem


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color


import androidx.compose.foundation.background

import androidx.compose.foundation.horizontalScroll

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding


import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.dummy.HomeDummyData


@Composable
fun VaultTabBar(

    tabs: List<VaultTabItem>,

    selectedTab: Int,

    onTabSelected: (Int) -> Unit
) {

    Row(

        modifier = Modifier

            .fillMaxWidth()

            .clip(
                RoundedCornerShape(50.dp)
            )

            .background(
                Color(0xFFF3F4F6)
            )

            .horizontalScroll(
                rememberScrollState()
            )

            .padding(
                horizontal = 4.dp,
                vertical = 6.dp
            )
    ) {

        tabs.forEach {

            VaultTab(

                item = it,

                selected = selectedTab == it.id,

                onClick = {

                    onTabSelected(it.id)
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 400
)
@Composable
private fun VaultTabBarPreview() {

    VaultTabBar(

        tabs = HomeDummyData.vaultTabs,

        selectedTab = 0,

        onTabSelected = {}
    )
}