package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.claimListSection


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimStatus
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.SubmittedClaim
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.claimListSection.component.TrackClaimCard
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

// Assuming you already have your AppColors imported:
// import com.policyboss.customer.ui.theme.AppColors

@Composable
fun MyClaimsListSection(
    claims: List<SubmittedClaim>,
    onFileClaimClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Total claims: ${claims.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextSecondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp) // Space for the floating button
            ) {
                items(claims, key = { it.id }) { claim ->
                    TrackClaimCard(claim = claim)
                }
            }
        }

        // Floating Action Button for "File a claim"
        ExtendedFloatingActionButton(
            onClick = onFileClaimClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 2.dp), // Controls the float spacing
            containerColor = Color.White,
            contentColor = AppColors.TextPrimary,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            ),
            shape = CircleShape
        ) {
            Text(
                text = "File a claim",
                style = MaterialTheme.typography.labelLarge,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Grey circular background with white plus icon
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(AppColors.Placeholder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Claim",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true, name = "My Claims List")
@Composable
fun MyClaimsListSectionPreview() {
    PolicyBossCustomerTheme() {
        // Mock data specifically for the preview to test all 3 badge colors
        val mockClaims = listOf(
            SubmittedClaim(
                id = "1",
                productType = AddPolicyType.CAR,
                status = ClaimStatus.UNDER_REVIEW,
                claimNumber = "#CLM-90234711",
                registrationNumber = "MH12AB4587",
                insurerName = "Tata AIG Insurance",
                createdDate = "22/01/2026",
                subStatus = "Claim registered"
            ),
            SubmittedClaim(
                id = "2",
                productType = AddPolicyType.BIKE,
                status = ClaimStatus.APPROVED,
                claimNumber = "#CLM-88334455",
                registrationNumber = "MH14XY9999",
                insurerName = "Bajaj Allianz",
                createdDate = "15/01/2026",
                subStatus = "Payment processed"
            ),
            SubmittedClaim(
                id = "3",
                productType = AddPolicyType.HEALTH,
                status = ClaimStatus.REJECTED,
                claimNumber = "#CLM-77221100",
                registrationNumber = "N/A",
                insurerName = "HDFC Ergo",
                createdDate = "10/01/2026",
                subStatus = "Documents missing"
            )
        )

        MyClaimsListSection(
            claims = mockClaims,
            onFileClaimClick = {}
        )
    }
}