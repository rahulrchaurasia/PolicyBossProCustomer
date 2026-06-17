package com.policyboss.customer.feature.home.component.home.vaultSection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpiryBadge(

    text: String,

    modifier: Modifier = Modifier
) {

    Box(

        modifier = modifier

            .clip(
                RoundedCornerShape(8.dp)
            )

            .background(
                Color(0xFFFFA726)
            )

            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
    ) {

        Text(

            text = text,

            color = Color.White,

            fontSize = 12.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ExpiryBadgePreview() {
    ExpiryBadge(
        text = "Expires in 7 Days"
    )
}