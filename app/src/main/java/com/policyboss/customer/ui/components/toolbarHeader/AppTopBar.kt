package com.policyboss.customer.ui.components.toolbarHeader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.button.OutlinedIconButton
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    trailingIcon: Painter? = null,
    onTrailingClick: (() -> Unit)? = null
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding() // <-- This pushes the content below the time/battery
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Left
        if (onBackClick != null) {
            OutlinedIconButton(
                icon = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "Back",
                onClick = onBackClick,
                iconTint = AppColors.White,
                modifier = Modifier.size(38.dp)
            )
        } else {
            // Reserve the same space to keep the title centered
            Spacer(modifier = Modifier.size(38.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        if (trailingIcon != null && onTrailingClick != null) {

            OutlinedIconButton(
                icon = trailingIcon,
                contentDescription = "Action",
                onClick = onTrailingClick,
                modifier = Modifier.size(38.dp)
            )

        } else {

            Spacer(
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AppTopBarPreview() {

    AppTopBar(
        title = "Add Bank Account",
        onBackClick = {},
        trailingIcon = painterResource(
            id = R.drawable.ic_sync
        ),
        onTrailingClick = {}
    )
}