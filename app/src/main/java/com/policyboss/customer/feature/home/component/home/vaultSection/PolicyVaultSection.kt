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

///////


// ---------------------------- IMPORTS ----------------------------

/*
        He can have:

        Motor -> 3 policies

        Bike -> 1 policy

        CV -> 0 policies

        Health -> 2 policies

        Life -> 1 policy

        Travel -> 0 policies

        SMELINE -> 4 policies

        So architecture is:

    VaultTabItem (1)
      |
      |
      |-----> many VaultPolicy
 */


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.vault.VaultTabItem


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.component.home.vaultSection.component.EmptyVaultState
import com.policyboss.customer.feature.home.component.home.vaultSection.component.MotorPolicyCard
import com.policyboss.customer.feature.home.component.home.vaultSection.component.VaultTabBar
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.feature.home.model.vault.VaultTabIds
import com.policyboss.customer.ui.theme.AppColors



@Composable
fun PolicyVaultSection(

    modifier: Modifier = Modifier,

    selectedTab: Int,

    policies: List<VaultPolicy> ,

    onTabSelected: (Int) -> Unit,

    onViewAllClick: () -> Unit,

    onRenewClick: (VaultPolicy) -> Unit,

    onViewDetailsClick: (VaultPolicy) -> Unit
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

            //tabs = vaultTabs,
            tabs = HomeDummyData.vaultTabs,
            selectedTab = selectedTab,

            onTabSelected = onTabSelected
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(

            text = "Total policies: ${policies.size}",

            style = MaterialTheme.typography.bodyMedium,

            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

       //HorizontalPager

        if (policies.isEmpty()) {

            EmptyVaultState()

        }
        else {

            HorizontalPager(

                state = rememberPagerState {

                    policies.size
                },

                pageSpacing = 0.dp,

                modifier = Modifier.fillMaxWidth()

            ) { page ->

                val policy = policies[page]

                MotorPolicyCard(

                    policy = policy,

                    onRenewClick = {

                        onRenewClick(policy)
                    },

                    onViewDetailsClick = {

                        onViewDetailsClick(policy)
                    }
                )
            }
        }
        ///
    }
}

@Composable
private fun HeaderRow(

    onViewAllClick: () -> Unit
) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(

            text = "Policy Vault",

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.SemiBold
        )


        Row(

            modifier = Modifier
                .clickable {
                    onViewAllClick()
                }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(

                text = "View All",

                style = MaterialTheme.typography.labelLarge,



                color = AppColors.BluePrimary,

                modifier = Modifier.padding(4.dp)
            )
            Spacer(

                modifier = Modifier.width(2.dp)
            )

            Icon(

                painter = painterResource(
                    id = R.drawable.ic_chevron_right
                ),

                contentDescription = null,

                tint = AppColors.BluePrimary,

                modifier = Modifier.size(20.dp)
            )
        }

    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PolicyVaultSectionPreview() {

    PolicyVaultSection(

        policies = HomeDummyData.vaultPolicies,

        selectedTab = VaultTabIds.MOTOR,

        onTabSelected = {},

        onViewAllClick = {},

        onRenewClick = {},

        onViewDetailsClick = {}
    )
}