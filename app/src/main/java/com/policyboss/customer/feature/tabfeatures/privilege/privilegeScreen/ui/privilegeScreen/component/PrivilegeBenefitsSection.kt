package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.PrimaryYellow
import com.policyboss.customer.ui.theme.gradients.AppGradients
import com.policyboss.customer.ui.theme.labelSmallSemiBold


// 1. Defined outside the composable so it doesn't re-allocate on recomposition
private val DefaultPrivilegeBenefits = listOf(
    "Earn instantly with zero hassle",
    "See what each policy can earn you",
    "Get smart, curated policy options",
    "Help your network renew & 3X your income potential"
)
@Composable
fun PrivilegeBenefitsSection(
    modifier: Modifier = Modifier,
    benefits: List<String> = _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.DefaultPrivilegeBenefits
)
{

    Column(modifier = modifier.padding(horizontal = 20.dp)) {

        // 1. Section Title with Gradient Dividers
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
           // Box(Modifier.weight(1f).height(1.dp).drawBehind { drawRect(leftGradient) })
            Box(
                Modifier
                    .weight(1f)
                    .height(1.dp)
                    .drawBehind { drawRect(AppGradients.fadeToLeft(AppColors.PrimaryYellow)) }
            )
            Text(
                text = "GET INDIA-FIRST EXCLUSIVE BENEFITS",
                color = PrimaryYellow,
                style = MaterialTheme.typography.labelSmallSemiBold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

           // Box(Modifier.weight(1f).height(1.dp).drawBehind { drawRect(rightGradient) })
            Box(
                Modifier
                    .weight(1f)
                    .height(1.dp)
                    .drawBehind { drawRect(AppGradients.fadeToRight(AppColors.PrimaryYellow)) }
            )
        }

        // 2. Yellow Benefits Card
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AppColors.YellowBackground,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Column(modifier = Modifier.padding(20.dp)) {

                // Iterate through the static list
                benefits.forEach { benefitText ->
                    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeBenefitItem(
                        text = benefitText
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // ==========================================
                // NEW: Gradient Divider above the price
                // ==========================================

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    // Left side fading in
                    Box(
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                            .drawBehind { drawRect(AppGradients.fadeToLeft(Color.Black.copy(alpha = 0.3f))) }
                    )
                    // Right side fading out
                    Box(
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                            .drawBehind { drawRect(AppGradients.fadeToRight(Color.Black.copy(alpha = 0.3f))) }
                    )
                }

                // 3. Pricing Section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Join the privilege for ₹999",
                       style = MaterialTheme.typography.displayMedium,
                        color = AppColors.GoldText,
                        fontSize = 16.sp

                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Vector XML icon loaded directly
                    Icon(
                        painter = painterResource(id = R.drawable.ic_free),
                        contentDescription = "Free tag",
                        tint = Color.Unspecified, // Preserves XML built-in colors
                        modifier = Modifier.height(30.dp).width(61.dp)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeBenefitsSectionPreview() {
    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeBenefitsSection()
}