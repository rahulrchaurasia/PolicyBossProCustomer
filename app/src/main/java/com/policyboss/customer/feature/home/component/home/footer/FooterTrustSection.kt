package com.policyboss.customer.feature.home.component.home.footer



// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FooterTrustSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        Text(
            text = "Comparison +\nConvenience +\nAdvisory = Us ❤️",
            style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF98A1B2)),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        val trustMarkers = listOf(
            "8+yrs in the insurance, 1L+ happy users",
            "Unbiased comparisons, transparent advisory",
            "Associated with 50+ insurers"
        )

        trustMarkers.forEach { marker ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    Icons.Default.CheckCircle, 
                    contentDescription = "Check", 
                    tint = Color(0xFF12B76A), // Success Green
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = marker, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ---------------- FOOTER TRUST SECTION PREVIEW ----------------

@Preview(showBackground = true)
@Composable
fun FooterTrustSectionPreview() {

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {

            FooterTrustSection()
        }
    }
}