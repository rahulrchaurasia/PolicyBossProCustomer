package com.policyboss.customer.ui.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size

import androidx.compose.ui.unit.dp


@Composable
fun TrustBadgeText(text: String) {
    Text(
        text = text,
        color = Color(0xFF15B79E),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TrustBadgeSeparator() {
    Box(
        modifier = Modifier
            .size(4.dp)
            .background(Color(0xFF15B79E))
    )
}