package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.ClaimSupportMenu

import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@Composable
fun ClaimSupportMenuItem(
    menu: ClaimSupportMenu,
    onClick: () -> Unit
) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .clickable(onClick = onClick)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(menu.icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = menu.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall,

            )

            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = AppColors.TextSecondary
            )
        }

        HorizontalDivider(
            color  = AppColors.BorderSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ClaimSupportMenuItemPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportMenuItem(
            menu = ClaimSupportMenu.FILE_GUIDE,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ClaimSupportMenuSectionPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportMenuSection(
            onAction = {}
        )
    }
}