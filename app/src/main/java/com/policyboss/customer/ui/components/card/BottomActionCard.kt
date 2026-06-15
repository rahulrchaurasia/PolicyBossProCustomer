package com.policyboss.customer.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.policyboss.customer.ui.components.PagerIndicator
import com.policyboss.customer.ui.components.TrustBadgesSection
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.feature.onboarding.model.OnboardingPage
import com.policyboss.customer.ui.theme.textPrimary
import com.policyboss.customer.ui.theme.textSecondary


// ============================================
// BOTTOM CARD (SWIPES WITH PAGE)
// ============================================




@Composable
fun BottomActionCard(
    page: OnboardingPage,
    pageIndex: Int,
    totalPages: Int,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {

    val cardShape = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 32.dp
    )

    Column(
        modifier = modifier
            .fillMaxWidth()

            // ✅ SHADOW
            .shadow(
                elevation = 10.dp,
                shape = cardShape
            )

            // ✅ CARD BACKGROUND
            .background(
                color = Color.White,
                shape = cardShape
            )

            .navigationBarsPadding()
    ) {

        Spacer(Modifier.height(20.dp))

        // Indicator
        PagerIndicator(
            totalPages = totalPages,
            currentPage = pageIndex
        )

        Spacer(Modifier.height(16.dp))

        // ✅ TEXT SECTION (FIXED)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = page.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            page.subtitle?.let {
                Spacer(Modifier.height(6.dp))

                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Button
        PrimaryCTAButton(
            text = if (pageIndex == totalPages - 1) "Get Started" else "Continue",
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(16.dp))

        TrustBadgesSection()
    }
}



