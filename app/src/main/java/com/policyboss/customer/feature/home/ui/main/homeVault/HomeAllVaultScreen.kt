package com.policyboss.customer.feature.home.ui.main.homeVault


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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.component.home.vaultSection.component.MotorPolicyCard
import com.policyboss.customer.feature.home.component.home.vaultSection.component.VaultTabBar
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.ui.components.collapsing.CollapsingScaffold

@Composable
fun HomeAllVaultDummyScreen(
    contentPadding: PaddingValues, // Used for system bottom nav if any
    selectedTab: Int,
    policies: List<VaultPolicy>,
    onTabSelected: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRenewClick: (VaultPolicy) -> Unit,
    onViewDetailsClick: (VaultPolicy) -> Unit
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    // The Scaffold handles ALL the math and nested scrolling!
    CollapsingScaffold(
        expandedHeaderHeight = 220.dp,
        collapsedHeaderHeight = 56.dp,
        pinnedContentHeight = 52.dp, // This makes the TabBar sticky automatically
        bottomPadding = contentPadding.calculateBottomPadding() + 24.dp,

        // 1. Define what the shrinking header looks like
        collapsingContent = { collapseFraction, currentHeaderHeight ->

            // Calculate alphas locally based on the fraction provided by the scaffold
            val expandedAlpha = (1f - collapseFraction * 1.6f).coerceIn(0f, 1f)
            val collapsedAlpha = ((collapseFraction - 0.7f) * 3.3f).coerceIn(0f, 1f)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(currentHeaderHeight)
                    .shadow(if (collapseFraction > 0.9f) 8.dp else 0.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                // EXPANDED TEXT
                if (expandedAlpha > 0.01f) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 56.dp + statusBarHeight, start = 24.dp, end = 24.dp, bottom = 16.dp)
                            .alpha(expandedAlpha),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Policy Vault", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Manage and track all your insurance policies...", color = Color.White.copy(alpha = 0.8f))
                    }
                }

                // COLLAPSED TOOLBAR
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp + statusBarHeight)
                        .padding(top = statusBarHeight) // First apply the top padding
                        .padding(horizontal = 16.dp),   // Then apply the horizontal padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(42.dp)) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Policy Vault",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.weight(1f).alpha(collapsedAlpha)
                    )
                    Spacer(modifier = Modifier.size(42.dp)) // Balancing spacer
                }
            }
        },

        // 2. Define the Sticky Tab Bar
        pinnedContent = {
            Surface(
                modifier = Modifier.fillMaxWidth().height(52.dp),
                color = MaterialTheme.colorScheme.background,
                // Optional: You could pass down collapseFraction if you wanted a shadow here
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    VaultTabBar(
                        tabs = AppDummyData.vaultTabs,
                        selectedTab = selectedTab,
                        onTabSelected = onTabSelected
                    )
                }
            }
        },

        // 3. Define the List (Layer 1)
        bodyContent = { dynamicPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = dynamicPadding // The Scaffold calculates this perfectly!
            ) {
                item {
                    Text(
                        text = "Total Policies: ${policies.size}",
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                    )
                }

                if (policies.isEmpty()) {
                    item { Text("No policies found", modifier = Modifier.padding(64.dp)) }
                } else {
                    items(items = policies, key = { it.id }) { policy ->
                        MotorPolicyCard(
                            policy = policy,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                            onRenewClick = { onRenewClick(policy) },
                            onViewDetailsClick = { onViewDetailsClick(policy) }
                        )
                    }
                }
            }
        }
    )
}