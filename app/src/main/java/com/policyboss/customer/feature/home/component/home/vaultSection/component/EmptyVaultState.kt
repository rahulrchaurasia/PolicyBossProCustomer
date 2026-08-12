package com.policyboss.customer.feature.home.component.home.vaultSection.component


// labelSmall AppTypographyStyle  and bodyMedium


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.button.AddPolicyButton

@Composable
fun EmptyVaultState(

    modifier: Modifier = Modifier

) {

    Column(

        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Image(

            painter = painterResource(R.drawable.ic_no_policy),

            contentDescription = null
        )

        Text(

            text = "No policies found",

            style = MaterialTheme.typography.labelSmall,

            modifier = Modifier.padding(top = 16.dp)
        )

        Text(

            text = "Build your policy vault today!",

            style = MaterialTheme.typography.bodyMedium,


            modifier = Modifier.padding(top = 8.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyVaultStatePreview() {

    MaterialTheme {

        EmptyVaultState()
    }
}