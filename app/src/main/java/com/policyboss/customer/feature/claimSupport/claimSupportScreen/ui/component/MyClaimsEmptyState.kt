package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.button.AddPolicyButton
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun MyClaimsEmptyState(
    onFileClaimClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        // REPLACE .fillMaxSize() WITH .defaultMinSize()
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 400.dp), // Forces it to be tall enough to look centered
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // This will now center the content within the 400.dp height
    ) {
        Image(
            painter = painterResource(R.drawable.ic_claim_support),
            contentDescription = null
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "No claims filed",
            style = MaterialTheme.typography.bodySmall,

            color = AppColors.TextSecondary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "You haven't filed any insurance claims yet.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        AddPolicyButton(

            modifier = Modifier.fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp),
            onClick = {

                onFileClaimClick()
            }

        )




    }
}