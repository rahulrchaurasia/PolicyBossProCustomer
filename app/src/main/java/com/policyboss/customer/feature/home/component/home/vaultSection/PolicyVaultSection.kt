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
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.vault.VaultTabItem


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.component.home.vaultSection.component.MotorPolicyCard
import com.policyboss.customer.feature.home.component.home.vaultSection.component.VaultTabBar
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.ui.theme.AppColors


private val vaultTabs = listOf(

    VaultTabItem(
        0,
        "Motor",
        R.drawable.ic_motor01
    ),

    VaultTabItem(
        1,
        "Bike",
        R.drawable.ic_bike01
    ),

    VaultTabItem(
        2,
        "CV",
        R.drawable.ic_cv01
    ),

    VaultTabItem(
        3,
        "Health",
        R.drawable.ic_health01
    ),

    VaultTabItem(
        4,
        "Life",
        R.drawable.ic_life01
    ),

    VaultTabItem(
        5,
        "Travel",
        R.drawable.ic_travel01
    ),

    VaultTabItem(
        6,
        "SMELINE",
        R.drawable.ic_smeline01
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

@Composable
private fun HeaderRow(

    onViewAllClick: () -> Unit
) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            text = "Policy Vault",

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.Bold
        )

        Text(

            text = "View All",

            color = AppColors.BluePrimary,

            modifier = Modifier.padding(8.dp)
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

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PolicyVaultPreview() {

    val policy = VaultPolicy(

        vehicleName = "Honda Amaze",

        vehicleNumber = "MH 12 AB 3456",

        companyLogo = R.drawable.img_tata,

        carImage = R.drawable.ic_car_protect,

        idv = "₹5L",

        premium = "₹6,403",

        expiry = "21.02.26",

        daysLeft = "Expires in 21 days",

        title = "Your 'Honda Amaze' is now protected! Access your policy by syncing your email"
    )

    MaterialTheme {

        PolicyVaultSection(

            policy = policy,

            onViewAllClick = {},

            onTabSelected = {},

            onRenewClick = {},

            onViewDetailsClick = {},
            modifier = TODO(),
            selectedTab = TODO()
        )
    }
}