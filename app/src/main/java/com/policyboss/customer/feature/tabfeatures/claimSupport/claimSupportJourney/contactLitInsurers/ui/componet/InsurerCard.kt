package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.ui.componet

// Ensure these match your actual project structure

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.model.InsurerContactModel
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun InsurerCard(
    insurer: InsurerContactModel,
    isGridView: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        // 1. Keep subtle shadow, but add a explicit border to fix the "dim top" issue
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)), // Subtle gray/blue border
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        if (isGridView) {
            // GRID LAYOUT (2 Items per row)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = insurer.logoRes),
                    contentDescription = insurer.name,
                    modifier = Modifier.height(48.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = insurer.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColors.TextPrimary,
                    textAlign = TextAlign.Center,
                    // 2. Force exactly 2 lines of height for perfect grid alignment
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        } else {
            // LIST LAYOUT (1 Item per row)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = insurer.logoRes),
                    contentDescription = insurer.name,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 16.dp)
                )
                Text(
                    text = insurer.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ==========================================
// PREVIEWS
// ==========================================

private val dummyInsurer = InsurerContactModel(
    id = "1",
    name = "Acko General Insurance",
    logoRes = android.R.drawable.ic_menu_gallery // Built-in Android icon for preview
)

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC, name = "1. Grid Item View")
@Composable
private fun InsurerCardGridPreview() {
    MaterialTheme {
        InsurerCard(
            insurer = dummyInsurer,
            isGridView = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC, name = "2. List Item View")
@Composable
private fun InsurerCardListPreview() {
    MaterialTheme {
        InsurerCard(
            insurer = dummyInsurer,
            isGridView = false,
            onClick = {}
        )
    }
}