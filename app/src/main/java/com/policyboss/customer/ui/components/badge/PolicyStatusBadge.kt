package com.policyboss.customer.ui.components.badge

// 1. Fixed Color import for Compose
// 2. Added missing padding import
// 3. Use standard Compose dp


// 2. Added missing padding import
// 3. Use standard Compose dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


data class PolicyBadgeUi(
    val text: String,
    val backgroundColor: Color,
    val textColor: Color


)
@Composable
fun PolicyStatusBadge(

    text: String,

    backgroundColor: Color,

    textColor: Color,

    modifier: Modifier = Modifier

) {

    Box(

        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
            .background(backgroundColor)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {

        Text(

            text = text,

            style = MaterialTheme.typography.labelMedium,

            fontWeight = FontWeight.SemiBold,

            color = textColor
        )
    }
}
