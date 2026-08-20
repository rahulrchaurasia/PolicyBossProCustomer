package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui.component// ---------------------------------------------------------
// Sub-Components
// ---------------------------------------------------------
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.RequirementItem
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumNormal

@Composable
fun ChecklistItemRow(item: RequirementItem, isLastItem: Boolean) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Number Badge
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(AppColors.ClaimBadgeBg),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.id.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.ClaimBadgeText
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Texts
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,

                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,

                    color = AppColors.TextSecondary,

                    style = MaterialTheme.typography.bodyMediumNormal
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Checkmark Icon
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(AppColors.ClaimCheckmarkBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = AppColors.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        if (!isLastItem) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = AppColors.ClaimDivider,
                thickness = 1.dp
            )
        }
    }
}



