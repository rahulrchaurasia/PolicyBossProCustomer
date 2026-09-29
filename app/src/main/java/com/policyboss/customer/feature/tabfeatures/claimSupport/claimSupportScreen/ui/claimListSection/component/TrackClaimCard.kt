package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.claimListSection.component
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.ClaimStatus
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.SubmittedClaim
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType

import com.policyboss.customer.ui.components.badge.CornerStatusBadge
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun TrackClaimCard(claim: SubmittedClaim) {
    // Determine Badge Color dynamically based on the Enum
    val badgeColor = when (claim.status) {
        ClaimStatus.APPROVED -> AppColors.SuccessGreen
        ClaimStatus.REJECTED -> AppColors.RedBadges
        ClaimStatus.UNDER_REVIEW -> AppColors.PrimaryYellow
        else -> AppColors.Gray500
    }

    // Determine Icon based on Product Type
    val productIcon = when (claim.productType) {
        AddPolicyType.CAR -> R.drawable.ic_car
        AddPolicyType.BIKE -> R.drawable.ic_bike
        AddPolicyType.CV -> R.drawable.ic_cv
        AddPolicyType.HEALTH -> R.drawable.ic_health
        AddPolicyType.LIFE -> R.drawable.ic_life
        AddPolicyType.TRAVEL -> R.drawable.ic_travel
        AddPolicyType.SMELINE -> R.drawable.ic_smeline
    }

    // State to handle the "Connect with your Insurer" expansion
    var isInsurerExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // 🚀 WRAP CONTENT IN A BOX FOR CORNER ALIGNMENT
        Box(modifier = Modifier.fillMaxWidth()) {

            // MAIN CONTENT COLUMN (Maintains internal padding)
            Column(modifier = Modifier.padding(16.dp)) {

                // 1. Header Row (Icon ONLY now)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = productIcon),
                        contentDescription = claim.productType.name,
                        tint = AppColors.BrandBlue,
                        modifier = Modifier.size(32.dp)
                    )


                }

                Spacer(modifier = Modifier.height(20.dp))

                // 2. Claim Detail Rows
                ClaimDetailRow("Claim", claim.claimNumber, isValueBold = true)
                ClaimDetailRow("Registration Number", claim.registrationNumber, isValueBold = true)
                ClaimDetailRow("Insurer", claim.insurerName, isValueBold = false)
                ClaimDetailRow("Claim created", claim.createdDate, isValueBold = false)
                ClaimDetailRow("Sub status", claim.subStatus, isValueBold = false)

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = AppColors.BorderSecondary)
                Spacer(modifier = Modifier.height(16.dp))

                // 3. Expandable Insurer Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { isInsurerExpanded = !isInsurerExpanded }
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Connect with your Insurer",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )
                        Icon(
                            imageVector = if (isInsurerExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            tint = AppColors.TextPrimary
                        )
                    }

                    AnimatedVisibility(
                        visible = isInsurerExpanded,
                                enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(),
                        exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
                    ) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            ClaimDetailRow("Business hours", claim.businessHours, isValueBold = false)

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Contact number", color = AppColors.TextSecondary, style = MaterialTheme.typography.bodyMedium)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = claim.contactNumber,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = AppColors.TextPrimary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_phone), // Make sure you have a phone icon
                                        contentDescription = "Call",
                                        tint = AppColors.TextPrimary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 4. View Details Button
                PrimaryCTAButton(
                    text = "View details",
                    textStyle = MaterialTheme.typography.labelMediumSemiBold,
                    onClick = { },
                )
            } // End of Column

            // 🚀 NEW CORNER BADGE IMPLEMENTATION
            // Placed inside the Box, aligned to TopEnd so it ignores the Column padding
            CornerStatusBadge(
                text = claim.status.displayName,
                badgeColor = badgeColor,
                modifier = Modifier.align(Alignment.TopEnd)
            )

        } // End of Box
    } // End of Card
}


// ==========================================
// PREVIEW
// ==========================================
@Preview(showBackground = true, backgroundColor = 0xFFF4F6F8,  name = "Track Claim Card")
@Composable
fun TrackClaimCardPreview() {
    PolicyBossCustomerTheme {
        val dummyClaim = SubmittedClaim(
            id = "1",
            productType = AddPolicyType.CAR,
            status = ClaimStatus.UNDER_REVIEW,
            claimNumber = "#CLM-90234711",
            registrationNumber = "MH12AB4587",
            insurerName = "Tata AIG Insurance",
            createdDate = "22/01/2026",
            subStatus = "Claim registered",
            businessHours = "8AM - 6 PM",
            contactNumber = "+91 97467 80291"
        )

        TrackClaimCard(claim = dummyClaim)
    }
}