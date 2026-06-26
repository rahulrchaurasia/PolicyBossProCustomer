package com.policyboss.customer.feature.home.component.home.footer.TrustedPartnersSection


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R

import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.offset


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrustedPartnersSection(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(380.dp)
    ) {

        // Background decorative layer
        Box(
            modifier = Modifier
                .matchParentSize()
//                .matchParentSize()
//                .alpha(0.08f)
//                .blur(0.5.dp)
        ) {


            // Background blur logos


            // Main logos

            PartnerLogoImage(
                logo = R.drawable.ic_footer1,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 24.dp, y = 16.dp)
                    .alpha(0.85f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_footer2,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-24).dp, y = 40.dp)
                    .alpha(0.7f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_footer3,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-40).dp)
                    .alpha(0.55f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_footer4,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 12.dp, y = 50.dp)
                    .alpha(0.45f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_footer5,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-16).dp, y = 40.dp)
                    .alpha(0.45f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_footer6,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    //.offset(y = (-60).dp)
                    .offset(x = (-24).dp, y = (-60).dp)
                    .alpha(0.35f)
            )

            PartnerLogoImage(
                logo = R.drawable.ic_claude,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 12.dp, y = (-20).dp)
                    .alpha(0.45f)
            )

        }
    }
}

// =================================================================
// PREVIEW
// =================================================================
@Preview(
    name = "Trusted Partners Section",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF, // Pure white background to match the app theme
    widthDp = 400
)
@Composable
fun TrustedPartnersSectionPreview() {
    MaterialTheme {
        // Wrapping it in a Box with padding to simulate how it looks
        // inside the HomeScreen LazyColumn
        Box(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            TrustedPartnersSection()
        }
    }
}