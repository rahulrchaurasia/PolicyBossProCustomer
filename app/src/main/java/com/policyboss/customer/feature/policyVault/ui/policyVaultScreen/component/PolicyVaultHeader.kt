package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun PolicyVaultHeader(

    onSyncMailClick: () -> Unit

) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically

    ) {

        Text(

            text = "Policy Vault",

            style = MaterialTheme.typography.headlineMedium,

            fontWeight = FontWeight.Bold
        )

        Row(

            modifier = Modifier.clickable {

                onSyncMailClick()

            },

            verticalAlignment = Alignment.CenterVertically

        ) {

            Text(

                text = "Sync mail",

                color = AppColors.BluePrimary,

                style = MaterialTheme.typography.titleSmall
            )

            Spacer(

                Modifier.width(4.dp)
            )

            Icon(
                painter = painterResource(R.drawable.ic_sync),

                contentDescription = null,

                tint = AppColors.BluePrimary
            )
        }
    }
}