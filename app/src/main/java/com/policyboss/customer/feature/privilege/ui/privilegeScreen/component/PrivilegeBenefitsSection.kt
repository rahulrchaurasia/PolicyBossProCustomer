package com.policyboss.customer.feature.privilege.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.policyboss.customer.ui.theme.AppColors.PrimaryYellow
import com.policyboss.customer.ui.theme.AppColors

@Composable
 fun PrivilegeBenefitsSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        // Section Title with flanking lines
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = DividerDefaults.Thickness,
                color = PrimaryYellow.copy(alpha = 0.5f)
            )
            Text(
                text = "GET INDIA-FIRST EXCLUSIVE BENEFITS",
                color = PrimaryYellow,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = DividerDefaults.Thickness,
                color = PrimaryYellow.copy(alpha = 0.5f)
            )
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = PrimaryYellow,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                PrivilegeBenefitItem("Earn instantly with zero hassle")
                PrivilegeBenefitItem("See what each policy can earn you")
                PrivilegeBenefitItem("Get smart, curated policy options")
                PrivilegeBenefitItem("Help your network renew & 3X your income potential")

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Join the privilege for ₹999",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(
                            text = "FREE",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeBenefitsSectionPreview() {
    PrivilegeBenefitsSection()
}