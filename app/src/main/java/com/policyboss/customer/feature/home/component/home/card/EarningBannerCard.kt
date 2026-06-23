package com.policyboss.customer.feature.home.component.home.card


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@Composable
fun EarningBannerCard(banner: EarningBanner, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable{
                onClick()
            }
            .background(AppColors.CardYellow)
            .padding(16.dp)
    ) {
        // Text at Top-Start
        Text(
            text = banner.title,
            color = AppColors.GoldText, // Darker brownish color for contrast
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            ),
            modifier = Modifier.align(Alignment.TopStart)
        )

        // Image at Bottom-End
        Image(
            painter = painterResource(id = banner.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.BottomEnd)
        )
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun EarningBannerCardPreview() {

    PolicyBossCustomerTheme {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            EarningBannerCard(
                banner = EarningBanner(
                    id = "1",
                    title = "Earnings instantly hit your account upon renewal",
                    imageRes = R.drawable.ic_launcher_foreground
                ),
                onClick = {}
            )
        }
    }

}