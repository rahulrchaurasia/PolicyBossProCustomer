package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.components.bullet.InfoBullet
import com.policyboss.customer.ui.theme.AppColors

@Composable
 fun DamagePhotoInformationBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, AppColors.HighlightCardBorder, RoundedCornerShape(12.dp))
            .background(AppColors.HighlightCardBackground, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Helps us process your claim faster",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = AppColors.WarningYellow
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoBullet("All angles of vehicle damage")
        InfoBullet("License plate")
        InfoBullet("Surrounding area/scene/roadsigns")
        InfoBullet("You can edit or add more photos later")
    }
}

@Preview(showBackground = true, name = "Info Box Preview")
@Composable
fun DamagePhotoInformationBoxPreview() {
    MaterialTheme {
        // Adding a little padding around it so it doesn't touch the preview edges
        Box(modifier = Modifier.padding(16.dp)) {
            DamagePhotoInformationBox()
        }
    }
}