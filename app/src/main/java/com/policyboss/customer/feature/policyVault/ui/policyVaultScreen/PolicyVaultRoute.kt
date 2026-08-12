package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiEvent
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiState
import com.policyboss.customer.feature.policyVault.viewmodel.PolicyVaultViewModel
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

/*
understand the whole flow

The Policy Vault has around 7 UI states.

Policy Vault
│
├── State 1
│     Empty State
│
├── State 2
│     Policies synced from mail (1 policy)
│
├── State 3
│     Multiple policies
│
├── State 4
│     Multiple pages inside one category
│
├── State 5
│     Manual Add Policy BottomSheet
│
├── State 6
│     Manual Policy Entry Screen
│
└── State 7
      Sorting BottomSheet

These are different states, not different screens.

Now let's analyze Figma carefully
Screen 1

No policy available

<img analysis omitted>

UI contains

Toolbar

Horizontal Tabs

Empty Illustration

"No policies found"

Add New Policy Button

Sync Mail Card

Bottom Navigation

So condition becomes

if(totalPolicies == 0)

show

EmptyState()

AddPolicyButton()

SyncMailCard()

Nothing else.

Screen 2

One policy after mail sync

Now UI changes.

Toolbar

Tabs

Total policies : 1

Policy Card

Add New Policy

Sync Mail Card

Notice

No sort

No expiry reminder

Because only one policy exists.

Rule becomes

if(totalPolicies == 1)

show

PolicyCard

AddPolicyButton

SyncMailCard
Screen 3

Two policies

Now Figma changes again.

New component appears.

Expiry Reminder Card

Now layout becomes

Toolbar

Tabs

Total Policies

Policy Cards

Expiry Reminder

Add Policy

Sync Mail

Sort

So

if(totalPolicies > 1)

Then

ReminderCard()

SortButton()

becomes visible.

Screen 4

Policies already active

Exactly same layout

Difference only

Card UI

Instead of

Expires in 21 days

Shows

Active Policy

So this is

NOT another screen.

Only

VaultPolicy.status

changes.

Example

enum class PolicyStatus

{

Active,

ExpiringSoon,

Expired

}

Card decides badge.

Screen 5

Add Policy BottomSheet

When user clicks

Add New Policy

Bottom sheet opens.

Choose

Car

Bike

CV

Health

Life

Travel

Smeline

No navigation yet.

Screen 6

User chooses

Car

Navigate

MotorInsuranceScreen

Now user enters

Car Number

OR

Policy Number

Confirm

Done.

Screen 7

Sort BottomSheet

Click

Sort

BottomSheet
 */


@Composable
fun PolicyVaultRoute(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues, // Passed from MainScreen Scaffold
    viewModel: PolicyVaultViewModel = hiltViewModel(),
    onNavigateToDetails: () -> Unit,

    // NEW: Add a callback for your new screen
    onNavigateToAddManualPolicy: (AddPolicyType) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {

        viewModel.uiEvent.collect { event ->

            when (event) {



                is PolicyVaultUiEvent.SyncMail -> {
                    //...
                }

                is PolicyVaultUiEvent.ViewPolicy -> {
                    //...
                }

                is PolicyVaultUiEvent.RenewPolicy -> {
                    //...
                }

                else -> {}
            }
        }
    }

    // Render the stateless screen
    PolicyVaultScreen(
        modifier = modifier,
        contentPadding = contentPadding,
        uiState = uiState,
        onAction = { action ->
            when (action) {

                is PolicyVaultAction.OnNavigateToDetailsClick -> {
                    onNavigateToDetails()
                }
                is PolicyVaultAction.OnAddPolicyTypeSelected -> {
                    onNavigateToAddManualPolicy(action.type)
                }
                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )
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