package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.ui.components.button.OutlinedIconButton
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun ClaimGuideScreen(
    productType: AddPolicyType,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Assuming you have custom colors defined in your theme, using hardcoded for reference
    val darkHeaderColor = Color(0xFF0B132B) 
    val textSecondaryColor = Color(0xFF9BA4B5)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(darkHeaderColor)
    ) {
        // --- 1. DARK HEADER SECTION ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp)) // Status bar spacing
            

            OutlinedIconButton(
                icon = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "Back",
                onClick = onBackClick,
                iconTint = AppColors.White,
                modifier = Modifier.size(38.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Subtitle
            Text(
                text = "5 mins read  •  5 mins read",
                color = textSecondaryColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            // This perfectly extracts the formatted string!
            Text(text = productType.displayTitle)
            Spacer(modifier = Modifier.height(8.dp))

            // 🚀 DYNAMIC TITLE based on product selection
            Text(
                text = "How to file a claim -\n${productType.name} Insurance",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }

        // --- 2. OVERLAPPING WHITE CONTENT SECTION ---
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                // Header Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray) // Placeholder color
                ) {
                    // Replace with AsyncImage (Coil) or actual painterResource
                    // Image(
                    //     painter = painterResource(id = R.drawable.your_warehouse_image),
                    //     contentDescription = "Claim Guide Header",
                    //     contentScale = ContentScale.Crop,
                    //     modifier = Modifier.fillMaxSize()
                    // )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Content Title
                Text(
                    text = "Insurance Claims Introduction",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1C2433)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Content Body (Modularized paragraphs for clean UI)
                GuideParagraph("Discover InsureWise, the modern solution for millennial insurance needs. Our platform offers a range of options, from basic coverage to comprehensive protection, all accessible from your smartphone.")
                GuideParagraph("Navigating insurance can be daunting for millennials. At InsureWise, we simplify the process, offering clear, customizable plans that fit your lifestyle. Whether it's protecting your first apartment, car, or even your side hustle, InsureWise provides the coverage you need with the flexibility you deserve.")
                GuideParagraph("At InsureWise, we understand that millennials need insurance that keeps pace with their dynamic lives. Our app combines ease of use with comprehensive coverage options. Whether you're insuring your tech gadgets, health, or travel adventures, InsureWise empowers you with the tools for a secure future.")
                
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

// Reusable component for consistent paragraph styling
@Composable
private fun GuideParagraph(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color(0xFF6B7280), // Slate gray for readability
        lineHeight = 20.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Preview(
    showBackground = true,
    name = "Claim Guide Screen - Motor",
    device = "id:pixel_7_pro" // Renders with standard mobile dimensions
)
@Composable
private fun ClaimGuideScreenPreview() {
    MaterialTheme {
        ClaimGuideScreen(
            modifier = Modifier.padding(),
            productType = AddPolicyType.SMELINE,
            onBackClick = {
                // Do nothing in preview
            }
        )
    }
}

@Preview(
    showBackground = true,
    name = "Claim Guide Screen - Health"
)
@Composable
private fun ClaimGuideScreenHealthPreview() {
    MaterialTheme {
        ClaimGuideScreen(
            modifier = Modifier.padding(),
            productType = AddPolicyType.SMELINE,
            onBackClick = {}
        )
    }
}