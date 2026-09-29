package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.model.RequirementItem
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.ui.component.BottomFooter
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.ui.component.ChecklistItemRow
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType

import com.policyboss.customer.ui.components.background.CurvedDarkBackground
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumNormal
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun FileClaimScreen(
    uiState: FileClaimUiState,
    onAction: (FileClaimAction) -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.ClaimLightBg)
    ) {
        // 1. Draw the Dark Curved Background
        CurvedDarkBackground()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 2. Top Navigation Bar
            AppTopBar(
                title = "",
                onBackClick = onBackClick,
                trailingIcon = painterResource(id = R.drawable.ic_close),
                onTrailingClick = onCloseClick,
                backIconTint = AppColors.White,
                trailingIconTint = AppColors.White
            )

            // 3. Scrollable Content Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Header Texts
                Text(
                    text = "File a Claim",
                    style = MaterialTheme.typography.labelLarge,

                    color = AppColors.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Before you begin, let's make sure you have everything you need",

                    color = AppColors.ClaimTextSecondaryDark,

                    style = MaterialTheme.typography.bodyMediumNormal

                )

                Spacer(modifier = Modifier.height(24.dp))

                // Dynamic Checklist Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = AppColors.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        uiState.requirements.forEachIndexed { index, item ->
                            ChecklistItemRow(
                                item = item,
                                isLastItem = index == uiState.requirements.lastIndex
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Info / Warning Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppColors.ClaimInfoBoxBg)
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Estimated time: 5-7 mins",

                            style = MaterialTheme.typography.labelMediumSemiBold,

                            color = AppColors.ClaimInfoBoxTitle
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Don't worry you can save your progress at any stage and come back anytime",
                            style = MaterialTheme.typography.bodyMediumNormal,
                            color = AppColors.ClaimInfoBoxDesc,

                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // 4. Fixed Footer
            BottomFooter(onContinueClick = onContinueClick)
        }
    }
}
// ─────────────────────────────────────────────────────────────────────────
// HELPER COMPOSABLES (Replace with your existing project components)
// ─────────────────────────────────────────────────────────────────────────




@Preview(showBackground = true, showSystemUi = true, name = "Health Claim Preview")
@Composable
fun FileClaimScreenHealthPreview() {
    MaterialTheme {
        FileClaimScreen(
            uiState = FileClaimUiState(
                isLoading = false,
                productType = AddPolicyType.HEALTH,
                requirements = listOf(
                    RequirementItem(
                        1,
                        "Patient Details",
                        "Health card, ID proof, Hospital details"
                    ),
                    RequirementItem(2, "Bills & Reports", "Discharge summary, Pharmacy bills, Test reports")
                )
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ },
            onCloseClick = { /* Do nothing in preview */ },
            onContinueClick = { /* Do nothing in preview */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Car Claim Preview")
@Composable
fun FileClaimScreenCarPreview() {
    MaterialTheme {
        FileClaimScreen(
            uiState = FileClaimUiState(
                isLoading = false,
                productType = AddPolicyType.CAR,
                requirements = listOf(
                    RequirementItem(1, "Accident Details", "Policy number, Date of Incident, location, etc"),
                    RequirementItem(2, "Other Parties", "Other driver's information (if applicable)"),
                    RequirementItem(3, "Documents", "Images of damage, police report (if applicable), license")
                )
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ },
            onCloseClick = { /* Do nothing in preview */ },
            onContinueClick = { /* Do nothing in preview */ }
        )
    }
}