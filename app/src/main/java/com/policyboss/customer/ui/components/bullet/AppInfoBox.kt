package com.policyboss.customer.ui.components.bullet


import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumNormal
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun AppInfoBox(
    title: String,
    descriptionItems: List<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.ClaimInfoBoxBg)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMediumSemiBold,
                color = AppColors.ClaimInfoBoxTitle
            )
            Spacer(modifier = Modifier.height(6.dp))
            descriptionItems.forEach { item ->
                Text(
                    text = "• $item",
                    style = MaterialTheme.typography.bodyMediumNormal,
                    color = AppColors.ClaimInfoBoxDesc,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AppInfoBoxPreview() {
    // You can wrap this inside your custom app theme (e.g., PolicyBossTheme { ... })
    // to ensure your AppColors and Typography render exactly as they do in the app.
    AppInfoBox(
        title = "Important Information",
        descriptionItems = listOf(
            "Please keep your vehicle registration number ready.",
            "Ensure the accident pictures are clear and visible.",
            "Do not move the vehicle from the accident spot if possible."
        ),
        modifier = Modifier.padding(16.dp)
    )
}