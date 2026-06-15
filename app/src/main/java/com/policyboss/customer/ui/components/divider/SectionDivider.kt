package com.policyboss.customer.ui.components.divider

// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionDivider(
    modifier: Modifier = Modifier
) {

    HorizontalDivider(
        modifier = modifier.padding(
            vertical = 16.dp,
            horizontal = 24.dp
        ),
        color = Color(0xFFEAECF0)
    )
}
// ---------------------------- PREVIEW ----------------------------

@Preview(showBackground = true)
@Composable
fun SectionDividerPreview() {

    SectionDivider()
}