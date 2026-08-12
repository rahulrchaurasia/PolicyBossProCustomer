package com.policyboss.customer.ui.components.divider


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OrDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Text(
            text = "OR",
            color = Color.Gray,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "OR Divider")
@Composable
fun OrDividerPreview() {
    MaterialTheme {
        // Added some padding so it doesn't touch the very edges of the preview window
        OrDivider(modifier = Modifier.padding(16.dp))
    }
}