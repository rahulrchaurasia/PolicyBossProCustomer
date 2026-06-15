package com.policyboss.customer.feature.home.component.home.section




// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.component.home.PolicyCategoryItem

@Composable
fun CuratedPoliciesSection() {
    Column {
        Text(text = "Curated Policies - Just for you", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Simplified grid mapping
        val policies = listOf("Car", "Bike", "CV", "Health", "Life", "Travel")

        // Chunking the list to create rows of 3
        policies.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { policy ->
                    PolicyCategoryItem(
                        modifier = Modifier.weight(1f),
                        title = policy,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CuratedPoliciesPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CuratedPoliciesSection()
        }
    }
}