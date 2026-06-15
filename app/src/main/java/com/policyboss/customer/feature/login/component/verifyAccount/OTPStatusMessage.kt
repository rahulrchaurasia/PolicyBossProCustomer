package com.policyboss.customer.feature.login.component.verifyAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

import com.policyboss.customer.ui.theme.AppTypography
import com.policyboss.customer.ui.theme.LightColorScheme

@Composable
fun OTPStatusMessage(
    icon: Int,
    message: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = color
        )
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Preview(showBackground = true)
@Composable
private fun OTPStatusMessagePreview_Default() {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            OTPStatusMessage(
                icon = R.drawable.ic_check_circle,
                message = "Success",
                color = Color.Green
            )
        }
    }
}