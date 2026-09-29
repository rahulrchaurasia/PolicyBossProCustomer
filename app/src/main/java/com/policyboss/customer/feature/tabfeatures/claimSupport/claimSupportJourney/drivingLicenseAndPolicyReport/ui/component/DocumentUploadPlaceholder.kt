package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun DocumentUploadPlaceholder(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8F9FA))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.FileUpload,
            contentDescription = "Upload document",
            tint = AppColors.TextPrimary,
            modifier = Modifier.size(32.dp)
        )
    }
}