package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiState
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.bottomSheet.AddPolicyBottomSheet
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.bottomSheet.SortByBottomSheet
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.PolicyVaultContent
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.PolicyVaultHeader
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.SortByButton
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card.PrivacyNote
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card.SyncPromoCard
import com.policyboss.customer.ui.components.button.AddPolicyButton
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.gradients.AppGradients
import kotlinx.coroutines.launch


@Composable
fun PolicyVaultScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: PolicyVaultUiState,
     // Callback to navigation graph
    onAction: (PolicyVaultAction) -> Unit // Callback to navigation graph
) {

    // 1. Declare the state for the Bottom Sheet here
    var showSortBottomSheet by remember { mutableStateOf(false) }

    // 2. NEW State for Add Policy Sheet
    var showAddPolicyBottomSheet by remember { mutableStateOf(false) }

    // For LazyColumn scroll State
    val listState = rememberLazyListState() //1. List STATE HERE
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppGradients.ScreenSurfaceGradient)
    )
    {

        LazyColumn(

            state = listState, // 2. ATTACH STATE HERE
            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(

                // 1. Get the safe area, then add 16.dp of visual breathing room
                top = contentPadding.calculateTopPadding() + 16.dp,

                bottom = contentPadding.calculateBottomPadding() + 24.dp
            )
        )
        {

            // Header
            item {
                PolicyVaultHeader(
                    onSyncMailClick = {
                        onAction(PolicyVaultAction.OnSyncMailClick)
                    }
                )
            }



            item {
                Spacer(
                    Modifier.height(20.dp)
                )

                PolicyVaultContent(

                    uiState = uiState,

                    onAction = onAction
                )
            }
            item {

//                Spacer(
//                    Modifier.height(24.dp)
//                )
                AddPolicyButton(

                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 24.dp)
                        .padding(horizontal = 16.dp),
                    onClick = {

                        showAddPolicyBottomSheet = true
                    }

                )

            }
                // 3. The Yellow Sync Promo Card
            item {
                Spacer(
                    Modifier.height(40.dp)
                )
                SyncPromoCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSyncClick = { onAction(PolicyVaultAction.OnSyncMailClick) }

                )
            }



            // 4. Privacy & Security Note
            item {
                 Spacer(Modifier.height(32.dp))

                PrivacyNote()

            }

            //  The Divider
            item {
                Spacer(Modifier.height(32.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = AppColors.Dividers // Light gray boundary
                )
            }

            // Place the Sort button below your content
            item {
                SortByButton(
                    onClick = { showSortBottomSheet = true } // Triggers the sheet
                )
            }

        }

    }

    if (showSortBottomSheet) {
        SortByBottomSheet(
            currentSelection = uiState.selectedSortOption,
            onOptionSelected = { selectedSort ->

                // A. Update the state in ViewModel
                // Send action to ViewModel
                onAction(PolicyVaultAction.OnSortOptionSelected(selectedSort))

                // B. Close the bottom sheet
                showSortBottomSheet = false

                // C. Smooth scroll to the top of the list
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }

            },
            onDismissRequest = {
                showSortBottomSheet = false
            }
        )
    }

    // . NEW Add Policy Sheet

    // ... inside PolicyVaultScreen

    if (showAddPolicyBottomSheet) {
        // Call the Wrapper dialog!
        AddPolicyBottomSheet(
            onDismissRequest = {
                showAddPolicyBottomSheet = false
            },
            onOptionSelected = { selectedType ->

                // 1. Fire action to ViewModel
                onAction(PolicyVaultAction.OnAddPolicyTypeSelected(selectedType))

                // 2. Close the sheet
                showAddPolicyBottomSheet = false
            }
        )
    }
}



@Preview(
    name = "Policy Vault Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PolicyVaultScreenPreview() {

    PolicyBossCustomerTheme {

        PolicyVaultScreen(
            modifier = Modifier,
            contentPadding = PaddingValues(),
            uiState = PolicyVaultUiState(),
            onAction = {}
        )
    }
}