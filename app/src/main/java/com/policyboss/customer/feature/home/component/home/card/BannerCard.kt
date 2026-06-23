package com.policyboss.customer.feature.home.component.home.card

// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors.BluePrimary
import com.policyboss.customer.ui.theme.AppColors.WarningYellow
import androidx.compose.foundation.Image

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

import  com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import com.policyboss.customer.ui.components.button.CardButton
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.captionSmall

@Composable
fun BannerCard(
    banner: PromoBanner,
    gradientColors: List<Color>,
    textColor: Color,
    modifier: Modifier = Modifier,
    onBannerClick: (PromoBanner) -> Unit
) {

    Box(
        modifier = modifier
            .width(300.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    colors = gradientColors
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = banner.tagText.uppercase(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.captionSmall,
                    color = textColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Image(
                    painter = painterResource(R.drawable.ic_policyboss_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)
                        .height(22.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 4.dp)
                ) {



                    Text(
                        text = banner.title,
                        style = MaterialTheme.typography.displayMedium,
                        color = textColor,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    CardButton(
                        text = banner.buttonText,
                        onClick = {
                            onBannerClick(banner)
                        }
                    )
                }

                Image(
                    painter = painterResource(banner.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BannerCardPreview() {

    val privilegeBanner = PromoBanner(
        id = "1",
        tagText = "RENEW & EARN",
        title = "Become a 'Privileged user' and earn instantly on renewals",
        buttonText = "Complete Setup",
        imageRes = R.drawable.ic_banner1,
        isYellowTheme = true,
        destination = BannerDestination.Privilege
    )

    val portfolioBanner = PromoBanner(
        id = "2",
        tagText = "BUILD YOUR POLICY PORTFOLIO",
        title = "Link and access all your policies in just one click",
        buttonText = "Sync Email",
        imageRes = R.drawable.ic_banner3,
        isYellowTheme = false,
        destination = BannerDestination.PolicyAction(
            action = BannerAction.RenewLife
        )
    )

    MaterialTheme {

        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {

            BannerCard(
                banner = privilegeBanner,
                gradientColors = listOf(
                    WarningYellow,
                    Color(0xFFF9A826)
                ),
                textColor = AppColors.GoldText,
                onBannerClick = {}
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            BannerCard(
                banner = portfolioBanner,
                gradientColors = listOf(
                    BluePrimary,
                    Color(0xFF7EBCFF)
                ),
                textColor = Color.White,
                onBannerClick = {}
            )
        }
    }
}