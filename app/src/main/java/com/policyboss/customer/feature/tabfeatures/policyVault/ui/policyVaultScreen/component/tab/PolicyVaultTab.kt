package com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.tab



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyVaultTabItem

import com.policyboss.customer.ui.theme.AppColors


@Composable
fun PolicyVaultTab(
    item: PolicyVaultTabItem,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) Color.White else Color.Transparent

    val textColor =
        if (selected) AppColors.TextPrimary
        else AppColors.TextSecondary

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        item.iconRes?.let {

            Image(
                painter = painterResource(it),
                contentDescription = item.category.title,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))
        }

        Text(
            text = item.category.title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            color = textColor
        )
    }
}

@Preview(
    name = "Policy Vault Tab Bar",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 420
)
@Composable
private fun PolicyVaultTabBarPreview() {

    MaterialTheme {

        PolicyVaultTabBar(

            tabs = AppDummyData.policyVaultTabs,

            selectedCategory = PolicyCategory.MOTOR,

            onTabSelected = {},
        )
    }
}