package com.policyboss.customer.feature.home.component.home.vaultSection


/*
******PolicyVaultSection :------>

home/components/policyvault/

PolicyVaultSection.kt

VaultModels.kt

VaultTabBar.kt

VaultTab.kt

MotorPolicyCard.kt

ExpiryBadge.kt

RenewNowButton.kt

PolicyDetailsCard.kt

PolicyHeaderImage.kt
 */


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


private val vaultTabs = listOf(

    VaultTabItem(
        0,
        "Motor",
        R.drawable.ic_motor
    ),

    VaultTabItem(
        1,
        "Bike",
        R.drawable.ic_bike
    ),

    VaultTabItem(
        2,
        "CV",
        R.drawable.ic_cv
    ),

    VaultTabItem(
        3,
        "Health",
        R.drawable.ic_health
    ),

    VaultTabItem(
        4,
        "Life",
        R.drawable.ic_life
    ),

    VaultTabItem(
        5,
        "Travel",
        R.drawable.ic_travel
    ),

    VaultTabItem(
        6,
        "SMELINE",
        R.drawable.ic_smeline
    )
)

@Composable
fun PolicyVaultSection(

    modifier: Modifier = Modifier,

    selectedTab: Int,

    policy: VaultPolicy,

    onTabSelected: (Int) -> Unit,

    onViewAllClick: () -> Unit,

    onRenewClick: () -> Unit,

    onViewDetailsClick: () -> Unit
) {

    Column(

        modifier = modifier.fillMaxWidth()
    ) {

        HeaderRow(
            onViewAllClick
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        VaultTabBar(

            tabs = vaultTabs,

            selectedTab = selectedTab,

            onTabSelected = onTabSelected
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(

            text = "Total policies: 1",

            style = MaterialTheme.typography.bodyMedium,

            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        MotorPolicyCard(

            policy = policy,

            onRenewClick = onRenewClick,

            onViewDetailsClick = onViewDetailsClick
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PolicyVaultPreview() {
//    MaterialTheme {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            PolicyVaultSection(
//                modifier = Modifier,
//                onViewAllClick = {}
//            )
//        }
//    }
//}