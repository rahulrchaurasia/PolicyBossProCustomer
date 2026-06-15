package com.policyboss.customer.feature.home.component.home.footer


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun BosspediaSection(
    modifier: Modifier = Modifier,
    onExploreMoreClick: () -> Unit
) {
    // Chain the passed modifier with any internal modifiers (like fillMaxWidth)
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "BOSSpedia",
                style = MaterialTheme.typography.titleMedium
            )
            // Apply the click event here!
            Text(
                text = "Explore more >",
                color = AppColors.BluePrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onExploreMoreClick() }
                    .padding(4.dp) // Optional padding for a better click target
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Articles Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(AppColors.CardBackground)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ARTICLES",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "View more >",
                    style = MaterialTheme.typography.labelSmall.copy(color = AppColors.TextPrimary)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scrollable Articles Row
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(3) {
                    ArticleCard()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun BosspediaAndFooterPreview() {

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F4F6))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            BosspediaSection(
                modifier = Modifier,
                onExploreMoreClick = {}
            )

            FooterTrustSection()
        }
    }
}